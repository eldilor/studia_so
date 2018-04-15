package zadanie2;

import services.Helper;
import services.QuickSorter;

import java.util.ArrayList;

public class Disc {
    private int[] memory;
    private int memoryCapacity;
    private int currentPosition;
    private boolean isLeftDirection = true;

    public Disc(int[] memory, int memoryCapacity, int currentPosition) {
        this.memory = memory;
        this.memoryCapacity = memoryCapacity;
        this.currentPosition = currentPosition;
    }

    public Disc(int[] memory, int memoryCapacity, int currentPosition, boolean isLeftDirection) {
        this.memory = memory;
        this.memoryCapacity = memoryCapacity;
        this.currentPosition = currentPosition;
        this.isLeftDirection = isLeftDirection;
    }

    public int[] getMemory() {
        return memory;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void applyFcfs() {
        int movesSum = 0, i = 0, startingCurrentPosition = this.currentPosition;
        int[] memory = this.memory;

        while (i < memory.length) {
            movesSum += Math.abs(this.currentPosition - memory[i]);
            this.currentPosition = memory[i++];
        }

        this.currentPosition = startingCurrentPosition;

        System.out.println("FCFS: " + movesSum);
    }

    public void applySstf() {
        int movesSum = 0, steps = 0, startingCurrentPosition = this.currentPosition;
        int[] memory = this.memory;

        while (steps < memory.length) {
            int closestElementIndex = Helper.findClosestIntIndexInArrayExcept(startingCurrentPosition, memory, -1);

            if (closestElementIndex >= 0) {
                movesSum += Math.abs(this.currentPosition - memory[closestElementIndex]);
                this.currentPosition = memory[closestElementIndex];
                memory[closestElementIndex] = -1;
            }

            steps++;
        }

        this.currentPosition = startingCurrentPosition;

        System.out.println("SSTF: " + movesSum);
    }

    public void applyScan() {
        int movesSum = 0, startingCurrentPosition = this.currentPosition, i;
        int[] memory = this.memory;
        ArrayList<Integer> leftMemoryList = new ArrayList<>(), rightMemoryList = new ArrayList<>();

        for (i = 0; i < memory.length; i++) {
            if (memory[i] > this.currentPosition) {
                rightMemoryList.add(memory[i]);
            } else if (memory[i] <= this.currentPosition) {
                leftMemoryList.add(memory[i]);
            }
        }

        int[] leftMemory = new int[leftMemoryList.size() + 1], rightMemory = new int[rightMemoryList.size()];

        i = 0;
        for (int leftMemoryListItem : leftMemoryList) {
            leftMemory[i++] = leftMemoryListItem;
        }
        leftMemory[i] = 0;

        i = 0;
        for (int rightMemoryListItem : rightMemoryList) {
            rightMemory[i++] = rightMemoryListItem;
        }

        QuickSorter quickSorter = new QuickSorter(leftMemory);
        leftMemory = quickSorter.sort(false);
        quickSorter.setNumbersArray(rightMemory);
        rightMemory = quickSorter.sort();

        if (this.isLeftDirection) {
            for (i  = 0; i < leftMemory.length; i++) {
                movesSum += Math.abs(this.currentPosition - leftMemory[i]);
                this.currentPosition = leftMemory[i];
            }

            for (i  = 0; i < rightMemory.length; i++) {
                movesSum += Math.abs(this.currentPosition - rightMemory[i]);
                this.currentPosition = rightMemory[i];
            }
        } else {
            for (i  = 0; i < rightMemory.length; i++) {
                movesSum += Math.abs(this.currentPosition - rightMemory[i]);
                this.currentPosition = rightMemory[i];
            }

            for (i  = 0; i < leftMemory.length; i++) {
                movesSum += Math.abs(this.currentPosition - leftMemory[i]);
                this.currentPosition = leftMemory[i];
            }
        }

        this.currentPosition = startingCurrentPosition;

        System.out.println("SCAN: " + movesSum);
    }
}
