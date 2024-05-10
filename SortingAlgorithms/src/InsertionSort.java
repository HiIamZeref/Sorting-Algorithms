import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


public class InsertionSort {
    /**
     * Insertion Sort algorithm to sort an array of integers serially.
     * @param array the array to be sorted.
     */
    public static void serialInsertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = current;
        }
    }
    
    /**
     * Insertion Sort algorithm to sort an array of integers in parallel.
     * @param array the array to be sorted.
     * @param numThreads the number of threads to use.
     */
    public static void parallelInsertionSort(int[] array, int numThreads){
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        pool.invoke(new InsertionSortTask(array, 0, array.length));
        pool.shutdown();
    }

    private static class InsertionSortTask extends RecursiveAction {
        private int[] array;
        private int start;
        private int end;

        InsertionSortTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= 16) { // If too short, use serial insertion sort.
                insertionSort(array, start, end);
            } else {
                int mid = (start + end) / 2;
                invokeAll(new InsertionSortTask(array, start, mid),
                          new InsertionSortTask(array, mid, end));
                merge(array, start, mid, end);
            }
        }

        private void insertionSort(int array[], int start, int end) {
            for (int i = start + 1; i < end; i++) {
                int current = array[i];
                int j = i - 1;

                while (j >= start && array[j] > current) {
                    array[j + 1] = array[j];
                    j--;
                }

                array[j + 1] = current;
            }
        }

        private void merge(int[] array, int start, int mid, int end) {
            int[] temp = new int[end - start];
            int i = start, j = mid, k = 0;

            while (i < mid && j < end) {
                if (array[i] <= array[j]) {
                    temp[k++] = array[i++];
                } else {
                    temp[k++] = array[j++];
                }
            }

            while (i < mid) {
                temp[k++] = array[i++];
            }

            while (j < end) {
                temp[k++] = array[j++];
            }

            System.arraycopy(temp, 0, array, start, temp.length);
        }
        
    }
}
