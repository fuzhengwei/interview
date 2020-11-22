package org.itstack.interview.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack) @2020
 * <p>
 * 鹿鼎记：入宫
 * 演员表：韦小宝
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("韦小宝");
        list.add("阿珂");
        list.add("双儿");
        list.add("曾柔");
        list.add("建宁公主");
        list.add("沐建屏");
        list.add("方怡");
        list.add("苏荃");

        Semaphore semaphore = new Semaphore(4, true);
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    String user = list.remove(new Random().nextInt(list.size()));
                    System.out.println("韦小宝带着七个老婆，过着桃园生活。每天打打麻将、练练武术。麻将四人桌：" + user);
                    Thread.sleep(3000L);
                } catch (InterruptedException ignore) {
                } finally {
                    semaphore.release();
                }
            }).start();
        }

    }

}
