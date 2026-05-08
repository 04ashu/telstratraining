package main.java.com.playground.multithreading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println(
                        "Task " + taskId + " running on " +
                                Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            });
        }

        executor.shutdown();
    }

}
