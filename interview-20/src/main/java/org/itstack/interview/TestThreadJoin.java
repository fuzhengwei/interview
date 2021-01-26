package org.itstack.interview;

public class TestThreadJoin {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println("thread before");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("thread after");
        });
        thread.start();

        System.out.println("main begin！");
//        thread.join();
        System.out.println("main end！");
    }

}
