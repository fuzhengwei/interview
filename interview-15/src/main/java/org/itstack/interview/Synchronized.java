package org.itstack.interview;

import java.sql.SQLOutput;

public class Synchronized {

    int i = 0;

    public void test() {
        synchronized (this){
            System.out.println(i++);
        }
    }

    public synchronized void test02(){

    }

    public static void main(String[] args) {
        new Synchronized().test();
    }

}
