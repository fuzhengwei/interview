package org.itstack.interview.test;

public class ApiTest {

    public static void main(String[] args) {

        new Thread(() -> {
            // todo
        }).start();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("hi!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        System.out.println("");
    }

}
