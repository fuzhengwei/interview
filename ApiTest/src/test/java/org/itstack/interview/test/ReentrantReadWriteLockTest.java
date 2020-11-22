package org.itstack.interview.test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack) @2020
 *
 * 鹿鼎记：与皇上比武
 * 演员表：韦小宝、皇上、建宁公主
 */
public class ReentrantReadWriteLockTest {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final Lock readLock = readWriteLock.readLock();
    private static final Lock writeLock = readWriteLock.writeLock();

    private static Deque<String> deque = new ArrayDeque<>();

    public static String get() {
        readLock.lock();
        try {
            return deque.poll();
        } finally {
            readLock.unlock();
        }
    }

    public static void put(String value) {
        writeLock.lock();
        try {
            deque.add(value);
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                put("小擒拿");
                put("抓奶龙抓手");
                put("下脚绊");
                put("大别子");
                put("锁喉");
                put("扣眼珠子");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignore) {
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                System.out.println("韦小宝与皇上比武出招：" + get());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignore) {
                }
            }
        }).start();
    }

}
