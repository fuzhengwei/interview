package org.itstack.interview;

public class VolatileTest {

    public static void main(String[] args) throws InterruptedException {
        final Task task = new Task();

        Thread t1 = new Thread(task, "线程t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("开始通知线程停止");
                    task.stop = true; //修改stop变量值。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "线程t2");
        t1.start();  //开启线程t1
        t2.start();  //开启线程t2
        Thread.sleep(1000);
    }
}

class Task implements Runnable {
    volatile boolean stop = false;

    public void run() {
        long s = System.currentTimeMillis();
        while (!stop) {
            System.out.println("你好");
        }
        System.out.println("线程退出" + (System.currentTimeMillis() - s));
    }
}
