package main.java.com.playground.multithreading.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureGetExample {

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            Thread.sleep(2000);
            return "Task completed";
        };

        Future<String> future = executor.submit(task);

        System.out.println("Doing some other work...");

        String result = future.get(); // waits here

        System.out.println(result);

        executor.shutdown();
    }

}
