package main.java.com.playground.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;

//Task runs in different thread
//Main thread does NOT block
//Uses ForkJoinPool by default
public class CompletableFutureBasic {

    public static void main(String[] args) {

        CompletableFuture.runAsync(() -> {
            System.out.println(
                    "Running async task in: " +
                            Thread.currentThread().getName());
        });

        System.out.println(
                "Main thread: " +
                        Thread.currentThread().getName());

        // small delay so JVM doesn't exit immediately
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
    }

}
