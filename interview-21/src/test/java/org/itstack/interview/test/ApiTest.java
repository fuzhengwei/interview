package org.itstack.interview.test;

import org.itstack.interview.TestPool;
import org.itstack.interview.ThreadPoolTrader;
import org.junit.Test;

import java.util.concurrent.*;

public class ApiTest {


    //线程个数掩码位数，并不是所有平台int类型是32位，所以准确说是具体平台下Integer的二进制位数-3后的剩余位数才是线程的个数，
    private static final int COUNT_BITS = Integer.SIZE - 3;

    //线程最大个数(低29位)00011111111111111111111111111111
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;


    @Test
    public void test_Queue() {
        BlockingQueue<String> workQueue = new LinkedBlockingQueue<String>();
        workQueue.offer("1111");
        workQueue.offer("1112");
        workQueue.offer("1113");

        System.out.println(workQueue.poll());
        System.out.println(workQueue.poll());
        System.out.println(workQueue.poll());

    }

    @Test
    public void test_retry() {
        retry:
        for (; ; ) {
            for (int i = 0; i < 10; i++) {
                if (i == 8) {
                    System.out.println(i);
                    break retry;
                }
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY = (1 << COUNT_BITS) - 1;
        int RUNNING    = -1 << COUNT_BITS;
        int SHUTDOWN   =  0 << COUNT_BITS;
        int STOP       =  1 << COUNT_BITS;
        int TIDYING    =  2 << COUNT_BITS;
        int TERMINATED =  3 << COUNT_BITS;

        System.out.println(Integer.toBinaryString(RUNNING));
        System.out.println(Integer.toBinaryString(SHUTDOWN));
        System.out.println(Integer.toBinaryString(STOP));
        System.out.println(Integer.toBinaryString(TIDYING));
        System.out.println(Integer.toBinaryString(TERMINATED));

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
//        threadPoolExecutor.execute(() -> {
//            System.out.println("Hi 线程池！");
//        });
//        threadPoolExecutor.shutdown();
    }


    @Test
    public void test_newCachedThreadPool() {
        Executors.newCachedThreadPool();
    }

    @Test
    public void test_newScheduledThreadPool() {
        Executors.newScheduledThreadPool(10);
    }

    @Test
    public void test_newSingleThreadExecutor() {
        Executors.newSingleThreadExecutor();
    }

    @Test
    public void test_newFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(() -> {
            System.out.println("Hi 线程池！");
        });
    }

    @Test
    public void test_TestPool() {
        TestPool testPool = new TestPool(10, 10, new ArrayBlockingQueue<>(10));
        testPool.execute(() -> System.out.println("hi"));
    }

    @Test
    public void test_ThreadPoolTrader() {
        ThreadPoolTrader threadPoolTrader = new ThreadPoolTrader(10, 10, new ArrayBlockingQueue<Runnable>(10));
        threadPoolTrader.execute(() -> {
            System.out.println("hi!");
        });
    }

}
