package org.itstack.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        //设置信号量同时执行的线程数是5
        final Semaphore semp = new Semaphore(2);
        // 模拟20个客户端访问
        for (int index = 0; index < 8; index++) {
            final int NO = index;
            exec.execute(() -> {
                try {
                    //使用acquire()获取锁
                    semp.acquire();
                    System.out.println("Accessing: " + NO);
                    //睡眠1秒
                    Thread.sleep(2000);

                } catch (InterruptedException ignore) {
                }  finally {
                    //使用完成释放锁
                    semp.release();
                }
            });
        }
        // 退出线程池
        exec.shutdown();
    }
}
