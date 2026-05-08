package main.java.com.playground.multithreading.runnable;

public class RunnableBasicExample {

    public static void main(String[] args) {

        Runnable task = () -> {
            System.out.println(
                    "Task running in thread: " +
                            Thread.currentThread().getName());
        };

        Thread thread = new Thread(task);
        thread.start();

        System.out.println(
                "Main thread: " + Thread.currentThread().getName());
    }

}
