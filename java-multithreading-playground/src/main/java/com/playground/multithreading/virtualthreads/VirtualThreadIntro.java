package main.java.com.playground.multithreading.virtualthreads;

public class VirtualThreadIntro {

    public static void main(String[] args) {
        Thread vThread = Thread.startVirtualThread(() -> {
            System.out.println("Running in Virtual Thread: "
                    + Thread.currentThread());
        });

        try {
            vThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
