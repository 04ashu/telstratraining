package main.java.com.playground.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenApplyThenAcceptExample {

    public static void main(String[] args) {

        CompletableFuture
                .supplyAsync(() -> 5)
                .thenApply(num -> num * 2)
                .thenApply(num -> num + 10)
                .thenAccept(result ->
                        System.out.println("Final Result: " + result)
                );

        sleep();
    }

    private static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
    }

}
