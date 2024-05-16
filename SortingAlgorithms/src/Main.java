import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int numThreads = 10;
        int[] arraySizes = {10, 100, 1000, 10_000, 100_000, 1_000_000};

        try {
            // // Creating writers for each algorithm
            // PrintWriter pwBubbleSerial = new PrintWriter(new FileWriter("bubble_sort_serial.csv"));
            // PrintWriter pwBubbleParallel = new PrintWriter(new FileWriter("bubble_sort_parallel.csv"));
            // PrintWriter pwQuickSerial = new PrintWriter(new FileWriter("quick_sort_serial.csv"));
            // PrintWriter pwQuickParallel = new PrintWriter(new FileWriter("quick_sort_parallel.csv"));
            // PrintWriter pwMergeSerial = new PrintWriter(new FileWriter("merge_sort_serial.csv"));
            // PrintWriter pwMergeParallel = new PrintWriter(new FileWriter("merge_sort_parallel.csv"));
            // PrintWriter pwInsertionSerial = new PrintWriter(new FileWriter("insertion_sort_serial.csv"));
            // PrintWriter pwInsertionParallel = new PrintWriter(new FileWriter("insertion_sort_parallel.csv"));

            // // CSV Headers
            // String headerSerial = "Array Size,Execution Time (ms)";
            // String headerParallel = "Array Size,Number of Threads,Execution Time (ms)";
            // pwBubbleSerial.println(headerSerial);
            // pwBubbleParallel.println(headerParallel);
            // pwQuickSerial.println(headerSerial);
            // pwQuickParallel.println(headerParallel);
            // pwMergeSerial.println(headerSerial);
            // pwMergeParallel.println(headerParallel);
            // pwInsertionSerial.println(headerSerial);
            // pwInsertionParallel.println(headerParallel);


            // // Running the algorithms
            // for (int size : arraySizes) {
            //     // Generating a random array that will be used in all algorithms
            //     int[] originalArray = Utils.generateRandomArray(size);
            //     int[] arrayCopy;

            //     // Bubble Sort Serial
            //     arrayCopy = originalArray.clone();
            //     long startTime = System.currentTimeMillis();
            //     BubbleSort.serialBubbleSort(arrayCopy);
            //     long endTime = System.currentTimeMillis();
            //     pwBubbleSerial.println(size + "," + (endTime - startTime));
            //     System.out.println("Bubble Sort Serial -> Size: " + size + "; Time: "  + (endTime - startTime) + "ms");
            //     System.out.println();

            //     // Quick Sort Serial
            //     arrayCopy = originalArray.clone();
            //     startTime = System.currentTimeMillis();
            //     QuickSort.serialQuickSort(arrayCopy, 0, arrayCopy.length - 1);
            //     endTime = System.currentTimeMillis();
            //     pwQuickSerial.println(size + "," + (endTime - startTime));
            //     System.out.println("Quick Sort Serial -> Size: " + size + "; Time: "  + (endTime - startTime) + "ms");
            //     System.out.println();

            //     // Merge Sort Serial
            //     arrayCopy = originalArray.clone();
            //     startTime = System.currentTimeMillis();
            //     MergeSort.serialMergeSort(arrayCopy);
            //     endTime = System.currentTimeMillis();
            //     pwMergeSerial.println(size + "," + (endTime - startTime));
            //     System.out.println("Merge Sort Serial -> Size: " + size + "; Time: "  + (endTime - startTime) + "ms");
            //     System.out.println();

            //     // Insertion Sort Serial
            //     arrayCopy = originalArray.clone();
            //     startTime = System.currentTimeMillis();
            //     InsertionSort.serialInsertionSort(arrayCopy);
            //     endTime = System.currentTimeMillis();
            //     pwInsertionSerial.println(size + "," + (endTime - startTime));
            //     System.out.println("Insertion Sort Serial -> Size: " + size + "; Time: "  + (endTime - startTime) + "ms");
            //     System.out.println();

                
            //         for (int threads = 2; threads <= numThreads; threads++) {
            //         // Bubble Sort Parallel
            //         arrayCopy = originalArray.clone();
            //         startTime = System.currentTimeMillis();
            //         BubbleSort.parallelBubbleSort(arrayCopy, threads);
            //         endTime = System.currentTimeMillis();
            //         pwBubbleParallel.println(size + "," + threads + "," + (endTime - startTime));
            //         System.out.println("Bubble Sort Parallel -> Size: " + size + "; Threads: " + threads + "; Time: "  + (endTime - startTime) + "ms");
            //         System.out.println();

            //         // Quick Sort Parallel
            //         arrayCopy = originalArray.clone();
            //         startTime = System.currentTimeMillis();
            //         QuickSort.parallelQuickSort(arrayCopy, threads);
            //         endTime = System.currentTimeMillis();
            //         pwQuickParallel.println(size + "," + threads + "," + (endTime - startTime));
            //         System.out.println("Quick Sort Parallel -> Size: " + size + "; Threads: " + threads + "; Time: "  + (endTime - startTime) + "ms");
            //         System.out.println();

            //         // Merge Sort Parallel
            //         arrayCopy = originalArray.clone();
            //         startTime = System.currentTimeMillis();
            //         MergeSort.parallelMergeSort(arrayCopy, threads);
            //         endTime = System.currentTimeMillis();
            //         pwMergeParallel.println(size + "," + threads + "," + (endTime - startTime));
            //         System.out.println("Merge Sort Parallel -> Size: " + size + "; Threads: " + threads + "; Time: "  + (endTime - startTime) + "ms");
            //         System.out.println();

            //         // Insertion Sort Parallel
            //         arrayCopy = originalArray.clone();
            //         startTime = System.currentTimeMillis();
            //         InsertionSort.parallelInsertionSort(arrayCopy, threads);
            //         endTime = System.currentTimeMillis();
            //         pwInsertionParallel.println(size + "," + threads + "," + (endTime - startTime));
            //         System.out.println("Insertion Sort Parallel -> Size: " + size + "; Threads: " + threads + "; Time: "  + (endTime - startTime) + "ms");
            //         System.out.println();
            //     }
            // }

            // // Closing all writers
            // pwBubbleSerial.close();
            // pwBubbleParallel.close();
            // pwQuickSerial.close();
            // pwQuickParallel.close();
            // pwMergeSerial.close();
            // pwMergeParallel.close();
            // pwInsertionSerial.close();
            // pwInsertionParallel.close();

            // Running the Python script to plot the graphs
            Runtime.getRuntime().exec("python3 plot.py");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
