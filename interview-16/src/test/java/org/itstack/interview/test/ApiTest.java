package org.itstack.interview.test;

import org.itstack.interview.CLHLock;
import org.itstack.interview.MCSLock;
import org.itstack.interview.SpinLock;
import org.itstack.interview.TicketLock;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ApiTest {

    public static void main(String[] args) {
        ReentrantLock fairLock = new ReentrantLock(true);  // true：公平锁
        fairLock.lock();
        try {
            // todo
        } finally {
            fairLock.unlock();
        }
    }

    @Test
    public void test_AtomicReference() {
        AtomicReference<String> tail = new AtomicReference<>("abc");
        String def = tail.getAndSet("def");
        System.out.println(def);

        boolean b = tail.compareAndSet("def", "xyz");
        System.out.println(b);
        System.out.println(tail.get());
    }

    @Test
    public void test_CLHLock() throws InterruptedException {
        final Lock lock = new CLHLock();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            new Thread(new TestLock(lock), String.valueOf(i)).start();
        }
        Thread.sleep(100000);
    }

    @Test
    public void test_SpinLock() throws InterruptedException {
        final Lock lock = new SpinLock();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            new Thread(new TestLock(lock), String.valueOf(i)).start();
        }
        Thread.sleep(100000);
    }

    @Test
    public void test_TicketLock() throws InterruptedException {
        final Lock lock = new TicketLock();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            new Thread(new TestLock(lock), String.valueOf(i)).start();
        }
        Thread.sleep(100000);
    }

    @Test
    public void test_MCSLock() throws InterruptedException {
        final Lock lock = new MCSLock();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            new Thread(new TestLock(lock), String.valueOf(i)).start();
        }
        Thread.sleep(100000);
    }

    static class TestLock implements Runnable {

        private Lock lock;

        public TestLock(Lock lock) throws InterruptedException {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                Thread.sleep(1000);
                System.out.println(String.format("Thread %s Completed", Thread.currentThread().getName()));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }
}


