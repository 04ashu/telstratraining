package main.java.com.playground.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CombineFuturesExample {

    public static void main(String[] args) {

        CompletableFuture<Integer> f1 =
                CompletableFuture.supplyAsync(() -> 10);

        CompletableFuture<Integer> f2 =
                CompletableFuture.supplyAsync(() -> 20);

        f1.thenCombine(f2, Integer::sum)
                .thenAccept(result ->
                        System.out.println("Sum: " + result)
                );

        sleep();
    }

    private static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
    }

}
