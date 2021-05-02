package org.itstack.interview.test;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
public class ABTest {

    public static void main(String[] args) {
        new ClazzA();
    }

}

class ClazzA {

    private ClazzB b = new ClazzB();

}

class ClazzB {

    private ClazzA a = new ClazzA();

}