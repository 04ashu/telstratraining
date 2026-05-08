package main.java.com.playground.multithreading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorExample {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> System.out.println("Task 1"));
        executor.submit(() -> System.out.println("Task 2"));
        executor.submit(() -> System.out.println("Task 3"));

        executor.shutdown();
    }

}
