package org.itstack.interview.test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * WorkStealingPool
 * 工作窃取线程池
 * <p>
 * 假设共有三个线程同时执行, A, B, C
 * 当A,B线程池尚未处理任务结束,而C已经处理完毕,则C线程会从A或者B中窃取任务执行,这就叫工作窃取
 * 假如A线程中的队列里面分配了5个任务，而B线程的队列中分配了1个任务，当B线程执行完任务后，它会主动的去A线程中窃取其他的任务进行执行
 * WorkStealingPool 背后是使用 ForkJoinPool实现的
 */
public class Test_newWorkStealingPool {

    public static void main(String[] args) throws IOException {
        // CPU 核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        // workStealingPool 会自动启动cpu核数个线程去执行任务
        ExecutorService service = Executors.newWorkStealingPool();
        service.execute(new R(10000));  // 我的cpu核数为12 启动13个线程,其中第一个是1s执行完毕,其余都是2s执行完毕,
        // 有一个任务会进行等待,当第一个执行完毕后,会再次偷取第十三个任务执行
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            service.execute(new R(2000));
        }

        // 因为work stealing 是deamon线程,即后台线程,精灵线程,守护线程
        // 所以当main方法结束时, 此方法虽然还在后台运行,但是无输出
        // 可以通过对主线程阻塞解决
        System.in.read();
    }

    static class R implements Runnable {

        int time;

        R(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "  " + time);
        }
    }
}
