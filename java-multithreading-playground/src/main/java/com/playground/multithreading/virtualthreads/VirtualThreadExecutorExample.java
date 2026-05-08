package main.java.com.playground.multithreading.virtualthreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadExecutorExample {

    public static void main(String[] args) {

        try (ExecutorService executor =
                     Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 1; i <= 5; i++) {
                int taskId = i;
                executor.submit(() -> {
                    System.out.println("Task " + taskId +
                            " running on " + Thread.currentThread());
                });
            }

        } // executor auto-closes
    }

}
