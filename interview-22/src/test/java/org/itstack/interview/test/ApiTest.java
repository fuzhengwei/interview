package org.itstack.interview.test;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * <!-- 异步线程池 -->
 * <bean id="taskExecutor" class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
 * <!-- 核心线程数 -->
 * <property name="corePoolSize" value="100"/>
 * <!-- 最大线程数 -->
 * <property name="maxPoolSize" value="500"/>
 * <!-- 线程池维护线程所允许的空闲时间 -->
 * <property name="keepAliveSeconds" value="60"/>
 * <!-- 队列最大长度 >=mainExecutor.maxSize -->
 * <property name="queueCapacity" value="500"/>
 * <!-- AbortPolicy:直接抛出java.prop.concurrent.RejectedExecutionException异常 -->
 * <!-- CallerRunsPolicy:主线程直接执行该任务，执行完之后尝试添加下一个任务到线程池中，可以有效降低向线程池内添加任务的速度 -->
 * <!-- DiscardOldestPolicy:抛弃旧的任务、暂不支持；会导致被丢弃的任务无法再次被执行 -->
 * <!-- DiscardPolicy:抛弃当前任务、暂不支持；会导致被丢弃的任务无法再次被执行 -->
 * <property name="rejectedExecutionHandler">
 * <bean class="java.util.concurrent.ThreadPoolExecutor$CallerRunsPolicy"/>
 * </property>
 * </bean>
 */
public class ApiTest {

    @Test
    public void test_newCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
    }

    @Test
    public void test_newFixedThreadPool() {
        //创建线程池，容量是3
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //主线程创建4个任务
        for (int i = 1; i < 5; i++) {
            final int taskID = i;
            //任务加入线程池，让线程池内的线程去执行每一个任务
            threadPool.execute(() -> {
                for (int i1 = 0; i1 < 5; i1++) {
                    try {
                        Thread.sleep(500);
                        Thread.sleep(500); //为了测试出效果，让每次任务执行都需要一定时间
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //任务执行结束后打印一句话
                    System.out.println("第" + taskID + " 个任务的第" + i1 + " 次执行");
                }
            });
        }
        threadPool.shutdown();
        System.out.println("主线程执行结束");

    }

    @Test
    public void test_newScheduledThreadPool() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    }

    @Test
    public void test_newSingleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }

    @Test
    public void test_newWorkStealingPool() {
        ExecutorService executorService = Executors.newWorkStealingPool();
    }

    @Test
    public void test_ThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 0, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10));
        threadPoolExecutor.execute(() -> {
            System.out.println("hi");
        });
        threadPoolExecutor.shutdown();
    }

}
