package org.itstack.interview.test;

import org.junit.Test;

public class ApiTest {


    @Test
    public void test_Thread_State() {
        Thread.State aNew = Thread.State.NEW;
    }

    @Test
    public void test_State_NEW() {
        Thread thread = new Thread(() -> {
        });
        System.out.println(thread.getState());
    }

    @Test
    public void test_State_NEW_2_RUNNABLE() {
        Thread thread = new Thread(() -> {
            while (true) {
            }
        });
        System.out.println(thread.getState());
    }

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();

        Thread thread01 = new Thread(() -> {
            synchronized (obj) {
                try {
                    System.out.println("wait before");
                    obj.wait(100000);
                    System.out.println("wait after");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread01.start();

        while (true){
            Thread.sleep(1000);
            System.out.println(thread01.getState());

        }
    }

}


