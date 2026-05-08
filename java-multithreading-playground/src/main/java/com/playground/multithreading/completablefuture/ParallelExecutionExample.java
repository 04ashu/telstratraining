package main.java.com.playground.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;

//Run tasks in parallel
//Combine results
//PERFECT for microservices
//Cutting response time in half
public class ParallelExecutionExample {

    public static void main(String[] args) {

        System.out.println("Starts");
        CompletableFuture<String> userFuture =
                CompletableFuture.supplyAsync(() -> fetchUser());

        CompletableFuture<String> productFuture =
                CompletableFuture.supplyAsync(() -> fetchProduct());

        CompletableFuture<Void> combined =
                CompletableFuture.allOf(userFuture, productFuture)
                        .thenRun(() -> {
                            try {
                                System.out.println(
                                        userFuture.get() + " | " + productFuture.get()
                                );
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

        combined.join();
    }

    private static String fetchUser() {
        sleep(2000);
        return "User data";
    }

    private static String fetchProduct() {
        sleep(2000);
        return "Product data";
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

}
