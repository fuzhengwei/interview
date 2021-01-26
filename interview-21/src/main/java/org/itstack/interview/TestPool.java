package org.itstack.interview;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TestPool {

    private int coreSize,
            maxSize;
    private AtomicInteger running = new AtomicInteger(0);
    private BlockingQueue<Runnable> queue;

    public TestPool(int coreSize, int maxSize, BlockingQueue<Runnable> queue) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.queue = queue;
    }

    public void execute(Runnable runnable) {
        if (running.get() < coreSize) {
            if (!addWorker(runnable)) {
                reject();
            }
        } else {
            System.out.println("当前队列大小：" + queue.size());
            //添加任务到队列中
            if (!queue.offer(runnable)) {
                //如果添加失败则继续创建线程
                System.out.println("offer 失败,当前线程数：" + running.get());
                if (!addWorker(runnable)) {
                    reject();
                }
            }
        }
    }

    private void reject() {
        throw new RuntimeException("超出大小,当前线程数：" + running.get() + " 队列大小：" + queue.size());
    }

    private boolean addWorker(Runnable runnable) {
        //如果当前线程数大于最大数则创建失败
        if (running.get() >= maxSize) {
            return false;
        }
        Worker worker = new Worker(runnable);
        worker.start();
        return true;
    }


    private class Worker extends Thread {
        private Runnable runnable;

        public Worker(Runnable runnable) {
            this.runnable = runnable;
            //增加线程运行线程数
            System.out.println("创建线程:当前线程数:" + running.incrementAndGet());
        }

        @Override
        public void run() {
            try {
                while (true) {
                    //运行线程
                    runnable.run();
                    System.out.println("运行结束,当前线程数：" + running.get());
                    //如果当前运行线程数大于核心大小就退出线程
                    if (running.get() > coreSize) {
                        break;
                    } else {
                        //反之从队列里取数据，理论上至少存活coreSize个线程
                        try {
                            System.out.println("000000：队列大小：" + queue.size());
                            runnable = queue.take();
                            System.out.println("11111111：队列大小：" + queue.size());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } finally {
                //线程结束，减少运行数
                running.decrementAndGet();
                System.out.println("结束线程，当前线程数：" + running.get());
            }
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("main");
        TestPool pool = new TestPool(2, 2, new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 10; i++) {
            final int j = i;
            System.out.println("i=" + i + " " + Thread.currentThread().getName());
            pool.execute(() -> {
                try {
                    Thread.sleep(1500);
                    System.out.println("睡0.1秒 完成:" + j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}