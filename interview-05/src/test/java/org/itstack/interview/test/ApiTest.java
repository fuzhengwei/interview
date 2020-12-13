package org.itstack.interview.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ApiTest {

    public static void main(String[] args) {
        System.out.println("");
        Map<String, String> map = new HashMap<String, String>();
        map.put("", "");
        map.get("");
        map.remove("");


    }

    @Test
    public void test_Iterator() {
        Map<String, String> map = new HashMap<String, String>(64);
        map.put("24", "Idx：2");
        map.put("46", "Idx：2");
        map.put("68", "Idx：2");
        map.put("29", "Idx：7");
        map.put("150", "Idx：12");
        map.put("172", "Idx：12");
        map.put("194", "Idx：12");
        map.put("271", "Idx：12");

        System.out.println("排序01：");
        for (String key : map.keySet()) {
            System.out.print(key + " ");
        }

        map.put("293", "Idx：12");
        map.put("370", "Idx：12");
        map.put("392", "Idx：12");
        map.put("491", "Idx：12");
        map.put("590", "Idx：12");

        System.out.println("\n\n排序02：");
        for (String key : map.keySet()) {
            System.out.print(key + " ");
        }                      

        map.remove("293");
        map.remove("370");
        map.remove("392");
        map.remove("491");
        map.remove("590");

        System.out.println("\n\n排序03：");
        for (String key : map.keySet()) {
            System.out.print(key + " ");
        }
        
    }

    @Test
    public void test_idx12() {

        for (int i = 1; i < 1000; i++) {
            String key = String.valueOf(i);
            int hash = key.hashCode() ^ (key.hashCode() >>> 16);
            int idx = (64 - 1) & hash;

            if (idx == 2) {
               // System.out.println(i + " Idx：" + idx);
            }

            if (idx == 62) {
                System.out.println(i + " Idx：" + idx);
            }
        }

        /**
         * idx：12
         * 40
         * 51
         * 62
         * 73
         * 84
         * 95
         * 150
         * 161
         *
         * idx：2
         * 13
         * 24
         *
         * idx：7
         *
         * 18
         * 29
         * 90
         */

    }

}
