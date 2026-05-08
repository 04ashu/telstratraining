package main.java.com.playground.multithreading.virtualthreads;

public class VirtualVsPlatformComparison {

    public static void main(String[] args) {

        runPlatformThreads();
        runVirtualThreads();
    }

    static void runPlatformThreads() {
        long start = System.currentTimeMillis();

        for (int i = 1; i <= 1000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }).start();
        }

        long end = System.currentTimeMillis();
        System.out.println("Platform threads launch time: "
                + (end - start) + " ms");
    }

    static void runVirtualThreads() {
        long start = System.currentTimeMillis();

        for (int i = 1; i <= 100_000; i++) {
            Thread.startVirtualThread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            });
        }

        long end = System.currentTimeMillis();
        System.out.println("Virtual threads launch time: "
                + (end - start) + " ms");
    }

}
