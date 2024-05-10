import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSort {
    /**
     * Merge Sort algorithm to sort an array of integers serially.
     * @param array the array to be sorted.
     */
    public static void serialMergeSort(int[] array){
        if (array.length < 2) {
            return; // Base case for recursion: array is already sorted or unitary.
        }

        // Split the array into two halves
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        // Copy the elements into the two halves
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        // Recursively sort the two halves
        serialMergeSort(left);
        serialMergeSort(right);

        merge(array, left, right);
    }

    /**
     * Helper method to merge two sorted halves into a single sorted array.
     * @param array the resulting array after merging left and right.
     * @param left a sorted left half.
     * @param right a sorted right half.
     */
     private static void merge(int[] array, int[] left, int[] right){
            int i = 0, j = 0, k = 0;
    
            while (i < left.length && j < right.length) {
                if (left[i] <= right[j]) {
                    array[k++] = left[i++];
                } else {
                    array[k++] = right[j++];
                }
            }
    
            while (i < left.length) {
                array[k++] = left[i++];
            }
    
            while (j < right.length) {
                array[k++] = right[j++];
            }
     }


    /**
     * Merge Sort algorithm to sort an array of integers in parallel.
     * @param array the array to be sorted.
     * @param numThreads the number of threads to use.
     */
    public static void parallelMergeSort(int[] array, int numThreads) {
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        pool.invoke(new MergeSortTask(array, new int[array.length], 0, array.length));
        pool.shutdown();
    }

    private static class MergeSortTask extends RecursiveAction {
        private int[] array;
        private int[] temp;
        private int left;
        private int right;

        MergeSortTask(int[] array, int[] temp, int left, int right) {
            this.array = array;
            this.temp = temp;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute(){
            if (right - left < 2) {
                return;
            }

            int mid = (left + right) / 2;
            invokeAll(new MergeSortTask(array, temp, left, mid),
                      new MergeSortTask(array, temp, mid, right));
            merge(array, temp, left, mid, right);
        }
    }

    private static void merge(int[] array, int[] temp, int left, int mid, int right){
        System.arraycopy(array, left, temp, left, right - left);

        int i = left, j = mid, k = left;
        while (i < mid && j < right) {
            if (temp[i] <= temp[j]) {
                array[k++] = temp[i++];
            } else {
                array[k++] = temp[j++];
            }
        }

        System.arraycopy(temp, i, array, k, mid - i);
    }


    
}
