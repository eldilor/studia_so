package services;

public class Helper {
    public static int findClosestIntIndexInArrayExcept(int number, int[] arr, int except) {
        int searchedIndex = -1;

        if (arr.length > 0) {
            int minDistance = Helper.findMaximumInArray(arr) - Helper.findMinimumInArray(arr) + 1;

            for (int i = 0; i < arr.length; i++) {
                int currentDistance = Math.abs(number - arr[i]);

                if (currentDistance < minDistance && arr[i] != except && currentDistance != 0) {
                    minDistance = currentDistance;
                    searchedIndex = i;
                }
            }
        }

        return searchedIndex;
    }

    public static int findMaximumInArray(int[] arr) {

        int max = arr[0];

        for (int arrElement : arr) {
            if (arrElement > max) {
                max = arrElement;
            }
        }

        return max;
    }

    public static int findMinimumInArray(int[] arr) {
        int min = arr[0];

        for (int arrElement : arr) {
            if (arrElement < min) {
                min = arrElement;
            }
        }

        return min;
    }
}
