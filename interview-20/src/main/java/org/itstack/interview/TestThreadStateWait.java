package org.itstack.interview;

public class TestThreadStateWait {

    public static void main(String[] args) throws InterruptedException {

        Object obj = new Object();

        Thread thread01 = new Thread(() -> {
            synchronized (obj) {
                try {
                    System.out.println("wait before");
                    obj.wait();
                    System.out.println("wait after");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread01.start();
        System.out.println(thread01.getState());

        Thread thread02 = new Thread(() -> {
            synchronized (obj) {
                System.out.println("obj.notify()");
                obj.notify();
            }
        });

        thread02.start();
        System.out.println(thread01.getState());
        
        while (thread01.getState() != Thread.State.TERMINATED) {
            Thread.sleep(100);
        }
        
        System.out.println(thread01.getState());

    }

}
