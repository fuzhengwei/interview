package org.itstack.interview;

public class ThreadBLOCKED {

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();

        new Thread(() -> {
            synchronized (obj) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread thread = new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        while (true) {
            Thread.sleep(1000);
            System.out.println(thread.getState());
        }
    }

}
