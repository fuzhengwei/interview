package org.itstack.interview;

public class ThreadTIMED_WAITING {

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        Thread thread = new Thread(() -> {
            synchronized (obj) {
                try {
                    Thread.sleep(100000);
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
