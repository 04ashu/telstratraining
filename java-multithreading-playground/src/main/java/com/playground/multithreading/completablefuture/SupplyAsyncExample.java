package main.java.com.playground.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;

public class SupplyAsyncExample {

    public static void main(String[] args) {

        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("Computing...");
                    return 10 + 20;
                });

        future.thenAccept(result ->
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
