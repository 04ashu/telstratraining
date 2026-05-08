package main.java.com.playground.multithreading.virtualthreads;

public class VirtualThreadBlockingDemo {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        for (int i = 1; i <= 100_000; i++) {
            Thread.startVirtualThread(() -> {
                try {
                    Thread.sleep(1000); // BLOCKING call
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        long end = System.currentTimeMillis();
        System.out.println("Launched 100k virtual threads in "
                + (end - start) + " ms");
    }

}
