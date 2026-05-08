package main.java.com.playground.multithreading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorShutdownExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Task completed");
            } catch (InterruptedException ignored) {}
        });

        executor.shutdown();

        if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }

}
