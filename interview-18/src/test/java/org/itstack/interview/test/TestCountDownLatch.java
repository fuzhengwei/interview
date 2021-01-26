package org.itstack.interview.test;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);//将state置为3
        for (int i = 0; i < 2; i++){
            new Thread(new WorkerThread(latch)).start();
        }
        System.out.println("latch test begin");
        latch.await();//获取到共享锁
        System.out.println("latch test end");
    }

    static class WorkerThread implements Runnable {
        private CountDownLatch latch;
        public WorkerThread(CountDownLatch latch) {
            this.latch = latch;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " finished");
            latch.countDown();//将state值减1，最后一个线程将state减为0后，释放上面await方法获取到的共享锁。
        }
    }

}
