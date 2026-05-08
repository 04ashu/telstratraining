package main.java.com.playground.multithreading.basics;

public class ThreadLifecycleExample {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println(
                    "Thread running: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000); // TIMED_WAITING
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("State after creation: " + thread.getState());
        // NEW

        thread.start();

        System.out.println("State after start(): " + thread.getState());
        // RUNNABLE

        Thread.sleep(100); // allow thread to start

        System.out.println("State while sleeping: " + thread.getState());
        // TIMED_WAITING

        thread.join(); // wait for thread to finish

        System.out.println("State after completion: " + thread.getState());
        // TERMINATED
    }

}
