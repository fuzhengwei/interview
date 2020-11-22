package org.itstack.interview.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack) @2020
 *
 * 鹿鼎记：扬州丽春院
 * 演员表：韦小宝、韦春花、鳌大人、海大富、陈近南
 */
public class SynchronizedTest {

    private static ExecutorService 丽春院 = Executors.newFixedThreadPool(10);

    private static volatile boolean 老鸨 = false;

    public static class 客官 implements Runnable {

        private String 姓名;

        public 客官(String 姓名) {
            this.姓名 = 姓名;
        }

        @Override
        public void run() {
            try {
                清倌(姓名);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static synchronized void 清倌(String 姓名) throws InterruptedException {
        while (true){
            System.out.println("韦春花与" + 姓名 + "喝茶、吟诗、做对、聊风月！");
            if (老鸨){
                System.out.println("老鸨敲门：时间到啦！\r\n");
                老鸨 = false;
                break;
            }
            Thread.sleep(1000);
        }

    }

    private static List<String> list = Arrays.asList("鳌大人", "陈近南", "海大富");

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            丽春院.execute(new 客官(list.get(i)));
            Thread.sleep(3000);
            老鸨 = true;
        }
    }

}
