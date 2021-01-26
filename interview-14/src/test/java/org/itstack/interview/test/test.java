package org.itstack.interview.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class test {

    public static boolean sign = false;

    private final static Logger logger = LoggerFactory.getLogger(test.class);

    public static void main(String[] args) {


        Thread Thread01 = new Thread(() -> {
            int i = 0;
            while (!sign) {
                // logger.info("你好");
                i++;
                add(i);
            }
            //logger.info("你坏");
        });
        Thread Thread02 = new Thread(() -> {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignore) {
            }
            sign = true;
            logger.info("vt.sign = true  while (!sign)");

        });

        Thread01.start();
        Thread02.start();

    }

    static Lock lock = new ReentrantLock();

    public static /*synchronized*/ int add(int i) {
        lock.lock();
        try {
            return i + 1;
        } finally {
            lock.unlock();
        }
    }
}
