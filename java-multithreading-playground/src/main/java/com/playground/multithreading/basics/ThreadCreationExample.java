package main.java.com.playground.multithreading.basics;

public class ThreadCreationExample extends Thread{

    @Override
    public void run() {
        System.out.println(
                "Running thread: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {

        System.out.println("Main thread: " + Thread.currentThread().getName());

        ThreadCreationExample thread = new ThreadCreationExample();

        // starts a new thread
        thread.start();

        // this would NOT start a new thread
        // thread.run();
    }

}
