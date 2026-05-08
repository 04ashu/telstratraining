package main.java.com.playground.multithreading.runnable;

public class RunnableVsThread {

    static class MyThread extends Thread {
        public void run() {
            System.out.println("From Thread class");
        }
    }

    static class MyRunnable implements Runnable {
        public void run() {
            System.out.println("From Runnable");
        }
    }

    public static void main(String[] args) {

        // Thread approach
        new MyThread().start();

        // Runnable approach
        new Thread(new MyRunnable()).start();
    }

}
