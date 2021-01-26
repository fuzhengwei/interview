package org.itstack.interview;

public class TestThreadStateNew {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
        });
        System.out.println(thread.getState());
    }

}
