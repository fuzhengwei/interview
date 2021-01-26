package org.itstack.interview;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);

        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.execute(() -> {
                try {
                    int millis = new Random().nextInt(10000);
                    System.out.println("等待游客上船，耗时：" + millis + "(millis)");
                    Thread.sleep(millis);
                } catch (Exception ignore) {
                } finally {
                    latch.countDown(); // 完事一个扣减一个名额
                }
            });
        }
        // 等待检查
        latch.await();
        System.out.println("船长急躁了，开船!");
        // 关闭线程池
        exec.shutdown();
    }
}
