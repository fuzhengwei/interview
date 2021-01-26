package org.itstack.interview;

public class TestThreadStateRunnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
        });
        // 启动
        thread.start();
        System.out.println(thread.getState());
    }

}
