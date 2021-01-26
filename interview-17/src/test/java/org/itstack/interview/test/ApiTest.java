package org.itstack.interview.test;

import org.itstack.interview.SyncLock;
import org.itstack.interview.VO;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class ApiTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void test_Unsafe() throws Exception {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(VO.class).toPrintable());
        System.out.println("=================");
        Unsafe unsafe = getUnsafeInstance();
        VO vo = new VO();
        vo.a = 2;
        vo.b = 3;
        vo.d = new HashMap<>();
        long aoffset = unsafe.objectFieldOffset(VO.class.getDeclaredField("a"));
        System.out.println("aoffset=" + aoffset);
        // 获取a的值
        int va = unsafe.getInt(vo, aoffset);
        System.out.println("va=" + va);


    }

    @Test
    public void test_stateOffset() throws Exception {
        Unsafe unsafe = getUnsafeInstance();
        long state = unsafe.objectFieldOffset
                (AbstractQueuedSynchronizer.class.getDeclaredField("state"));
        System.out.println(state);
    }

    public static Unsafe getUnsafeInstance() throws Exception {
        // 通过反射获取rt.jar下的Unsafe类
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        // return (Unsafe) theUnsafeInstance.get(null);是等价的
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }

    @Test
    public void test_SyncLock() throws InterruptedException {
        final SyncLock lock = new SyncLock();
        for (int i = 0; i < 10; i++) {
            new Thread(new TestLock(lock), String.valueOf(i)).start();
        }
        Thread.sleep(100000);
    }

    static class TestLock implements Runnable {

        private SyncLock lock;

        public TestLock(SyncLock lock) throws InterruptedException {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                Thread.sleep(1000);
                System.out.println(String.format("Thread %s Completed", Thread.currentThread().getName()));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

}
