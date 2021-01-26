package org.itstack.interview;

public class ThreadYieldRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + " running" + i);
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        ThreadYieldRunnable threadYieldRunnable = new ThreadYieldRunnable();
        Thread t1 = new Thread(threadYieldRunnable);
        t1.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t1.yield");
    }
}
