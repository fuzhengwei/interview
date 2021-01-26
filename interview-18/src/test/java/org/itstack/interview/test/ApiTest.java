package org.itstack.interview.test;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

public class ApiTest {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2, true); // 构造函数入参，permits：信号量、fair：公平锁/非公平锁
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire(1);
                    System.out.println(Thread.currentThread().getName() + "蹲坑");
                    Thread.sleep(5000L);
                } catch (InterruptedException ignore) {
                } finally {
                    semaphore.release(1);
                }
            }, "蹲坑编号：" + i).start();
        }
    }

    @Test
    public void test_AbstractQueuedSynchronizer() throws InterruptedException {
        AbstractQueuedSynchronizer aqs = new AbstractQueuedSynchronizer() {
            public int tryAcquireShared(int acquires) {
                for (; ; ) {
                    int available = getState();
                    int remaining = available - acquires;
                    if (remaining < 0 ||
                            compareAndSetState(available, remaining))
                        return remaining;
                }
            }

        };
    }

    @Test
    public void test_ReadWriteLock() throws InterruptedException {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        reentrantReadWriteLock.writeLock().lock();

        reentrantReadWriteLock.readLock().lock();

        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();


        CountDownLatch countDownLatch = new CountDownLatch(2);
        countDownLatch.await();

    }

}
