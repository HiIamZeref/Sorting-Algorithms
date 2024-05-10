import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class BubbleSort {

    /**
     * Bubble sort algorithm to sort an array of integers.
     * @param array the array to be sorted.
     */
    public static void serialBubbleSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * Parallel bubble sort algorithm to sort an array of integers.
     * Based on the Odd Even Transposition Sort algorithm.
     * @param array the array to be sorted.
     * @param numThreads the number of threads to use.
     */
    public static void parallelBubbleSort(int[] array, int numThreads) {
        int n = array.length;
        AtomicBoolean sorted = new AtomicBoolean(false);
    
        while (!sorted.get()) {
            sorted.set(true);
    
            // Executar duas fases: 0 para índices pares e 1 para índices ímpares
            for (int phase = 0; phase < 2; phase++) {
                ExecutorService executor = Executors.newFixedThreadPool(numThreads);  // Criação do ExecutorService
                AtomicBoolean phaseSorted = new AtomicBoolean(true);
    
                for (int i = phase; i < n - 1; i += 2) {
                    int index = i;
                    executor.submit(() -> {
                        if (array[index] > array[index + 1]) {
                            int temp = array[index];
                            array[index] = array[index + 1];
                            array[index + 1] = temp;
                            phaseSorted.set(false);
                        }
                    });
                }
    
                executor.shutdown();
                try {
                    executor.awaitTermination(1, TimeUnit.DAYS);  // Aguardar até que todas as tarefas terminem
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
    
                // Se nenhuma troca foi feita nesta fase, o array pode estar ordenado
                if (phaseSorted.get()) {
                    sorted.set(false);
                }
            }
        }
    }
    
    
    
    

    
}
