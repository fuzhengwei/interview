package org.itstack.interview.test;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ApiTest {

    @Test
    public void test_LinkedList() {

        List<String> list01 = new LinkedList<String>();


        LinkedList<String> list02 = new LinkedList<String>();


        list02.add("a");
        list02.add(0, "1");
        list02.addFirst("");
        list02.addLast("");


    }

    @Test
    public void test_init() {
        // 初始化方式；普通方式
        LinkedList<String> list01 = new LinkedList<String>();
        list01.add("a");
        list01.add("b");
        list01.add("c");
        System.out.println(list01);

        // 初始化方式；Arrays.asList
        LinkedList<String> list02 = new LinkedList<String>(Arrays.asList("a", "b", "c"));
        System.out.println(list02);

        // 初始化方式；内部类
        LinkedList<String> list03 = new LinkedList<String>() {
            {
                add("a");
                add("b");
                add("c");
            }
        };
        System.out.println(list03);

        // 初始化方式；Collections.nCopies
        LinkedList<Integer> list04 = new LinkedList<Integer>(Collections.nCopies(10, 0));
        System.out.println(list04);
    }

    @Test
    public void test_ArrayList_addFirst() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            list.add(0, i);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        // 10万=406、100万=46288、1000万=
    }

    @Test
    public void test_LinkedList_addFirst() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            list.addFirst(i);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        // 10万=7、100万=37、10000万=5714
    }


    @Test
    public void test_ArrayList_addLast() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        // 10万=12、100万=44、1000万=2648
    }

    @Test
    public void test_LinkedList_addLast() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            list.addLast(i);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        // 10万=11、100万=45、1000万=5899
    }

    @Test
    public void test_ArrayList_addCenter() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            list.add(list.size() >> 1, i);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        // 10万=251、100万=22460、1000万=
    }

    @Test
    public void test_LinkedList_addCenter() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            list.add(list.size() >> 1, i);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        // 10万=11112、100万=45、1000万=？
    }

    @Test
    public void test_ArrayList() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(0, "a");
        list.add(0, "b");

        System.out.println(list);

    }

    @Test
    public void test_remove() {
        LinkedList<String> list = new LinkedList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        list.remove();
        list.remove(1);
        list.remove("a");
        list.removeFirst();
        list.removeLast();
        list.removeAll(Arrays.asList("a", "b"));
    }

    LinkedList<Integer> list = new LinkedList<Integer>();

    int xx = 0;
    @Before
    public void init() {
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
    }


    @Test
    public void test_LinkedList_for0() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            xx += list.get(i);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void test_LinkedList_for1() {
        long startTime = System.currentTimeMillis();
        for (Integer itr : list) {
            xx += itr;
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void test_LinkedList_Iterator() {
        long startTime = System.currentTimeMillis();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            xx += next;
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void test_LinkedList_forEach() {
        long startTime = System.currentTimeMillis();
        list.forEach(integer -> {
            xx += integer;
        });
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }


    @Test
    public void test_LinkedList_stream() {
        long startTime = System.currentTimeMillis();
        list.stream().forEach(integer -> {
            // do something
            xx += integer;
        });
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }

}
