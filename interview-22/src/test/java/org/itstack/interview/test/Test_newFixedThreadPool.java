package org.itstack.interview.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test_newFixedThreadPool {

    private static Logger logger = LoggerFactory.getLogger(Test_newFixedThreadPool.class);

    //  new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 1; i < 5; i++) {
            int groupId = i;
            executorService.execute(() -> {
                for (int j = 1; j < 5; j++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                    logger.info("第 {} 组任务，第 {} 次执行完成", groupId, j);
                }
            });
        }
        executorService.shutdown();
    }

}
