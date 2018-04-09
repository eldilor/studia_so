package zadanie2;

import services.Helper;

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
}
