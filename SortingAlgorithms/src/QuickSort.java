import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuickSort {

    private static final int THRESHOLD = 1000; // Limiar para usar sort direto em vez de mais divisões (Resolveu o problema de muitas chamadas)

    // Serial Quick Sort
    
    /**
     * Quick Sort algorithm to sort an array of integers serially.
     * @param array the array to be partitioned.
     * @param low the low index of the array.
     * @param high the high index of the array.
     */
    public static void serialQuickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = partition(array, low, high);
            serialQuickSort(array, low, pivot - 1);
            serialQuickSort(array, pivot + 1, high);
        }
    }

    // Partition and swap methods remain unchanged

    /**
     * Partition method to partition the array and return the pivot index.
     * @param array the array to be partitioned.
     * @param low the low index of the array.
     * @param high the high index of the array.
     * @return the pivot index.
     */
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Utility method to swap two elements in an array.
     * @param array the array in which elements will be swapped.
     * @param i the index of the first element.
     * @param j the index of the second element.
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Parallel Quick Sort

    /**
     * Quick Sort algorithm to sort an array of integers in parallel.
     * @param array the array to be sorted.
     * @param numThreads the number of threads to use.
     */
    public static void parallelQuickSort(int[] array, int numThreads) {
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        pool.invoke(new QuickSortTask(array, 0, array.length - 1));
        pool.shutdown();
    }

    /**
     * A recursive action for performing the QuickSort algorithm in parallel.
     */
    private static class QuickSortTask extends RecursiveAction {
        private int[] array;
        private int low;
        private int high;

        QuickSortTask(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low > THRESHOLD) {
                int pivotIndex = partition(array, low, high);
                QuickSortTask leftTask = new QuickSortTask(array, low, pivotIndex - 1);
                QuickSortTask rightTask = new QuickSortTask(array, pivotIndex + 1, high);
                invokeAll(leftTask, rightTask);
            } else {
                serialQuickSort(array, low, high);
            }
        }
    }
}
