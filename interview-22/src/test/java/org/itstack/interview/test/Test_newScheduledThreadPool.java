package org.itstack.interview.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class Test_newScheduledThreadPool {

    private static Logger logger = LoggerFactory.getLogger(Test_newScheduledThreadPool.class);

  
    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        executorService.schedule(() -> {
            logger.info("3秒后开始执行");
        }, 3, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(() -> {
            logger.info("3秒后开始执行，以后每2秒执行一次");
        }, 3, 2, TimeUnit.SECONDS);

        executorService.scheduleWithFixedDelay(() -> {
            logger.info("3秒后开始执行，后续延迟2秒");
        }, 3, 2, TimeUnit.SECONDS);

    }

}
