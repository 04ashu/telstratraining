package main.java.com.playground.multithreading.callable;

import java.util.concurrent.*;

public class CallableExceptionHandling {

    public static void main(String[] args) {

        Callable<Integer> task = () -> {
            throw new IllegalStateException("Something went wrong!");
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(task);

        try {
            future.get(); // exception thrown here
        } catch (ExecutionException e) {
            System.out.println("Exception from Callable: " +
                    e.getCause().getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

}
