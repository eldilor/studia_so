package zadanie2;

import java.util.Random;

public class App {
    public static void run() {
        Disc disc = App.generateDisc(200, 20);
//        int[] memory = {98,183,37,122,14,124,65,67};
//        Disc disc = new Disc(memory, 200, 53);
        disc.applyScan();
        System.out.println(disc);
    }

    private static Disc generateDisc(int memoryCapacity, int memoryRequests) {
        Random rand = new Random();
        int currentPosition = rand.nextInt(memoryCapacity + 1), i = 0;
        int[] memory = new int[memoryRequests];

        while (i < memoryRequests) {
            int randomMemoryCell = rand.nextInt(memoryCapacity + 1);
            boolean isOriginal = true;

            for (int memoryCell : memory) {
                if (memoryCell == randomMemoryCell || randomMemoryCell == currentPosition) {
                    isOriginal = false;
                    break;
                }
            }

            if (isOriginal) {
                memory[i] = randomMemoryCell;
            }
            i++;
        }

        return new Disc(memory, memoryCapacity, currentPosition);
    }
}
