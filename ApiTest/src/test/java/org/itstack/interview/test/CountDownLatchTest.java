package org.itstack.interview.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack) @2020
 * <p>
 * 鹿鼎记：天地会
 * 演员表：
 * 总舵主，陈近南
 * 莲花堂香主，蔡德忠；
 * 洪顺堂香主，方大洪；
 * 家后堂香主，马超兴；
 * 参太堂香主，胡德帝；
 * 宏化堂香主，李式开；
 * 青木堂香主，韦小宝；
 * 赤火堂香主，古至中；
 * 玄水堂香主，林永超；
 * 黄土堂香主，姚必达。
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        List<String> list = Arrays.asList("总舵主，陈近南", "莲花堂香主，蔡德忠",
                "洪顺堂香主，方大洪",
                "家后堂香主，马超兴",
                "参太堂香主，胡德帝",
                "宏化堂香主，李式开",
                "青木堂香主，韦小宝",
                "赤火堂香主，古至中",
                "玄水堂香主，林永超",
                "黄土堂香主，姚必达");

        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int idx = i;
            exec.execute(() -> {
                try {
                    System.out.println("天地会核心十堂核心成员，高层会议，成员：" + list.get(idx) + " 入场");
                    Thread.sleep(3000);
                } catch (Exception ignore) {
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        System.out.println("天地会，核心成员到齐。开会讨论谁入宫，偷取四十二章经！「内定韦香主」");
        exec.shutdown();
    }

}
