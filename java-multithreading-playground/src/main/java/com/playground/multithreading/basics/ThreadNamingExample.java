package main.java.com.playground.multithreading.basics;

public class ThreadNamingExample {

    public static void main(String[] args) {

        Thread worker1 = new Thread(() -> {
            System.out.println(
                    "Running -> " + Thread.currentThread().getName());
        });

        Thread worker2 = new Thread(() -> {
            System.out.println(
                    "Running -> " + Thread.currentThread().getName());
        });

        worker1.setName("Worker-Thread-1");
        worker2.setName("Worker-Thread-2");

        worker1.start();
        worker2.start();
    }

}
