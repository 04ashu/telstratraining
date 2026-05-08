package main.java.com.playground.multithreading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceBasic {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {
            System.out.println(
                    "Task executed by: " +
                            Thread.currentThread().getName());
        });

        executor.shutdown();
    }

}
