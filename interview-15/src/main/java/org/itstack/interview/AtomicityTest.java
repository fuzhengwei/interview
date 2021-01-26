package org.itstack.interview;

public class AtomicityTest {

    private static volatile int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int i1 = 0; i1 < 10000; i1++) {
                    add();
                }
            });
            thread.start();
        }
        // 等10个线程运行完毕
        Thread.sleep(1000);
        System.out.println(counter);
    }

    public static void add() {
        synchronized (AtomicityTest.class) {
            counter++;
        }
    }

}

