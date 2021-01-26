package org.itstack.interview.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class Test_newCachedThreadPool {

    private static Logger logger = LoggerFactory.getLogger(Test_newCachedThreadPool.class);

    // new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>())
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
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
        
        // 测试结果
    }

}
