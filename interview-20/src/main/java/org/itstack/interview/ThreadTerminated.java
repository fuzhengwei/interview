package org.itstack.interview;

public class ThreadTerminated {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
        });
        thread.start();

        System.out.println(thread.getState());
        System.out.println(thread.getState());
        System.out.println(thread.getState());
    }

}
