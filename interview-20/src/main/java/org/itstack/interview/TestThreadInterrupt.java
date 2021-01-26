package org.itstack.interview;

public class TestThreadInterrupt {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                if (!Thread.interrupted()) break;
                System.out.println("hihihi");
            }
        });

        thread.start();
        System.out.println("interrupt");
        thread.interrupt();

    }

}
