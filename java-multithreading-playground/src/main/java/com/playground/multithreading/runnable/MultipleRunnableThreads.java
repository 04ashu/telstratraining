package main.java.com.playground.multithreading.runnable;

public class MultipleRunnableThreads {

    public static void main(String[] args) {

        Runnable task = () -> {
            System.out.println(
                    "Running in: " + Thread.currentThread().getName());
        };

        Thread t1 = new Thread(task, "Worker-1");
        Thread t2 = new Thread(task, "Worker-2");
        Thread t3 = new Thread(task, "Worker-3");

        t1.start();
        t2.start();
        t3.start();
    }

}
