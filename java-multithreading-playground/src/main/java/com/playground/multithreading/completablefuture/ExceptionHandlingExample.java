package main.java.com.playground.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ExceptionHandlingExample {

    public static void main(String[] args) {

        CompletableFuture
                .supplyAsync(() -> {
                    throw new RuntimeException("Failure!");
                })
                .exceptionally(ex -> {
                    System.out.println("Error handled: " + ex.getMessage());
                    return -1;
                })
                .thenAccept(result ->
                        System.out.println("Result: " + result)
                );

        sleep();
    }

    private static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
    }

}
