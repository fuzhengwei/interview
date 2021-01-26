package org.itstack.interview.test;

public class Sort implements Runnable {

    private int number;

    public Sort(int number) {
        this.number = number;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{2, 30000000, 1, 6, 40000000, 5};
        for (final int number : numbers) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(number);
                        System.out.println(number);
                    } catch (InterruptedException ignore) {
                    }
                }
            }).start();
        }

        System.out.println(System.currentTimeMillis());
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.number);
            System.out.println(this.number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
