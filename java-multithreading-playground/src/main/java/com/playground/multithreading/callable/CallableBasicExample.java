package main.java.com.playground.multithreading.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableBasicExample {

    public static void main(String[] args) throws Exception {

        Callable<Integer> task = () -> {
            System.out.println(
                    "Running in thread: " + Thread.currentThread().getName());
            return 10 + 20;
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> future = executor.submit(task);

        Integer result = future.get(); // BLOCKS until result is available

        System.out.println("Result from Callable: " + result);

        executor.shutdown();
    }

}
