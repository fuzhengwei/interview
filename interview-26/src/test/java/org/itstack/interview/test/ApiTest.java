package org.itstack.interview.test;

public class ApiTest {
    public static void main(String[] args) {
        String[] strs = {"111", "22"};
        t(strs);
    }
    public static void t(String... strs) {
        System.out.println(strs.length);
    }
}
