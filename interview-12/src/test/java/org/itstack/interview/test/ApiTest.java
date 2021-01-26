package org.itstack.interview.test;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class ApiTest {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    private static AtomicInteger nextHashCode = new AtomicInteger();
    private static final int HASH_INCREMENT = 0x61c88647;

    @Test
    public void test_nextHashCode_00() {
        System.out.println(nextHashCode.getAndAdd(HASH_INCREMENT));
        System.out.println(nextHashCode.getAndAdd(HASH_INCREMENT));
        System.out.println(nextHashCode.getAndAdd(HASH_INCREMENT));
        System.out.println(nextHashCode.getAndAdd(HASH_INCREMENT));
        System.out.println(nextHashCode.getAndAdd(HASH_INCREMENT));


        /**
         * 1640531527
         * -1013904242
         * 626627285
         * -2027808484
         */
    }

    @Test
    public void test_nextHashCode_01() {
        System.out.println(0 * HASH_INCREMENT + HASH_INCREMENT);
        System.out.println(1 * HASH_INCREMENT + HASH_INCREMENT);
        System.out.println(2 * HASH_INCREMENT + HASH_INCREMENT);
        System.out.println(3 * HASH_INCREMENT + HASH_INCREMENT);
    }

    @Test
    public void test_idx() {
        int hashCode = 0;
        for (int i = 0; i < 8; i++) {
            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
            System.out.println((hashCode & 7) + " 斐波那契散列：" + (hashCode & 15) + " 普通散列：" + (String.valueOf(i).hashCode() & 15));
        }
    }

    @Test
    public void test_thread() {
        new ThreadLocal<>().set("a");
        new ThreadLocal<>().set("小傅哥");
        new ThreadLocal<>().set("b");

        new ThreadLocal<>().get();
    }

    @Test
    public void test_set_get() throws Exception {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        

        System.out.println(threadLocal.get());


    }

    @Test
    public void test_threadLocalHashCode() throws Exception {
        for (int i = 0; i < 15; i++) {
            ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
            Field threadLocalHashCode = objectThreadLocal.getClass().getDeclaredField("threadLocalHashCode");
            threadLocalHashCode.setAccessible(true);
            System.out.println("objectThreadLocal：" + (((int)threadLocalHashCode.get(objectThreadLocal)) & 15));
        }
    }

    @Test
    public void test_unsafe() {
        AtomicInteger atomicInteger = new AtomicInteger();

        System.out.println(atomicInteger.getAndAdd(0x61c88647));
        System.out.println(atomicInteger.getAndAdd(0x61c88647));
    }


    @Test
    public void test_() {

        new ThreadLocal<String>().set("小傅哥");

        threadLocal.set("小傅哥");
        threadLocal.set("12");
        threadLocal.set("13");
        threadLocal.set("14");
        String s = threadLocal.get();
        System.out.println(s);
        threadLocal.remove();
    }

    @Test
    public void test_00() {
        System.out.println(Thread.currentThread().getId());
        System.out.println(Thread.currentThread().getId());
        System.out.println(Thread.currentThread().getId());
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void test_gc2() throws Exception {

        new ThreadLocal<>().set("abc");


//        System.gc();


        Thread t = Thread.currentThread();
        Class<? extends Thread> clz = t.getClass();
        Field field = clz.getDeclaredField("threadLocals");
        field.setAccessible(true);
        Object threadLocalMap = field.get(t);
        Class<?> tlmClass = threadLocalMap.getClass();
        Field tableField = tlmClass.getDeclaredField("table");
        tableField.setAccessible(true);
        Object[] arr = (Object[]) tableField.get(threadLocalMap);
        for (Object o : arr) {
            if (o != null) {
                Class<?> entryClass = o.getClass();
                Field valueField = entryClass.getDeclaredField("value");
                Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                valueField.setAccessible(true);
                referenceField.setAccessible(true);
                System.out.println(String.format("弱引用key:%s,值:%s", referenceField.get(o), valueField.get(o)));
            }
        }

    }

    @Test
    public void test_gc() throws Exception {
//        Thread t = new Thread(()->test("abc",false));
//        t.start();
//        t.join();
        System.out.println("--gc后--");
//        Thread t2 = new Thread(() -> test("def", true));
//        t2.start();
        //t2.join();
        test("abc", false);
        test("def", true);
    }

    private static void test(String s, boolean isGC) {
        try {
//            new ThreadLocal<>().set(s);
            ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
            objectThreadLocal.set(s);
            if (isGC) {
                System.gc();
            }
            Thread t = Thread.currentThread();
            Class<? extends Thread> clz = t.getClass();
            Field field = clz.getDeclaredField("threadLocals");
            field.setAccessible(true);
            Object threadLocalMap = field.get(t);
            Class<?> tlmClass = threadLocalMap.getClass();
            Field tableField = tlmClass.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] arr = (Object[]) tableField.get(threadLocalMap);
            for (Object o : arr) {
                if (o != null) {
                    Class<?> entryClass = o.getClass();
                    Field valueField = entryClass.getDeclaredField("value");
                    Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                    valueField.setAccessible(true);
                    referenceField.setAccessible(true);
                    System.out.println(String.format("弱引用key:%s,值:%s", referenceField.get(o), valueField.get(o)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
