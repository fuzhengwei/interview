package org.itstack.interview;

public class ApiTest {

    public static void main(String[] args) {
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hi");
            }
        }).start();

     
    }

}
