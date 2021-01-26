package org.itstack.interview.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test_newSingleThreadExecutor {

    private static Logger logger = LoggerFactory.getLogger(Test_newSingleThreadExecutor.class);

    // new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
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
