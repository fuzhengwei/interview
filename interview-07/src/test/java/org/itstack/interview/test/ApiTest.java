package org.itstack.interview.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import sun.net.idn.Punycode;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Array;
import java.util.*;

public class ApiTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(Collections.nCopies(9, "a"));
        System.out.println("初始化：" + list);

        list.add(2, "b");
        System.out.println("插入后：" + list);
    }

    @Test
    public void t2() {
        String[] strs = new String[10];
        strs[5] = "1";

        Arrays.asList();
    }

    @Test
    public void test_remove() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");
        list.add("i");
        list.add("j");
        System.out.println(list);

        list.remove(2);

        System.out.println(list);
        System.out.println(list.size());
    }

    @Test
    public void test_arraycopy() {
        int[] oldArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] newArr = new int[oldArr.length + (oldArr.length >> 1)];

        System.arraycopy(oldArr, 0, newArr, 0, oldArr.length);

        newArr[11] = 11;
        newArr[12] = 12;
        newArr[13] = 13;
        newArr[14] = 14;

        System.out.println("数组元素：" + JSON.toJSONString(newArr));
        System.out.println("数组长度：" + newArr.length);

        /**
         * 测试结果
         *
         * 数组元素：[1,2,3,4,5,6,7,8,9,10,0,11,12,13,14]
         * 数组长度：15
         */
    }

    @Test
    public void test_copy_remove() {
        int[] oldArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int index = 2;
        int numMoved = 10 - index - 1;

        System.arraycopy(oldArr, index + 1, oldArr, index, numMoved);

        System.out.println("数组元素：" + JSON.toJSONString(oldArr));
    }

    @Test
    public void init_01() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
    }

    @Test
    public void init_02() {

        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        list.add("ddd");

        ArrayList<String> obj = new ArrayList<String>(Arrays.asList("aaa", "bbb", "ccc"));

    }

    @Test
    public void init_03() {
        ArrayList<String> list = new ArrayList<String>() {{
            add("aaa");
            add("bbb");
            add("ccc");
        }};
    }

    @Test
    public void init_04() {
        ArrayList<Integer> list = new ArrayList<Integer>(Collections.nCopies(10, 0));
    }

    @Test
    public void t() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        System.out.println("通过数组转换：" + (list1.toArray().getClass() == Object[].class));

        ArrayList<Integer> list2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        System.out.println("通过集合转换：" + (list2.toArray().getClass() == Object[].class));
    }


    @Test
    public void t_hashIdx() {

        List<String> list = new ArrayList<String>(Collections.<String>nCopies(8, "0"));

        list.set("a".hashCode() & 8 - 1, "a");
        list.set("b".hashCode() & 8 - 1, "b");
        list.set("c".hashCode() & 8 - 1, "c");
        list.set("d".hashCode() & 8 - 1, "d");
        list.set("e".hashCode() & 8 - 1, "e");
        list.set("f".hashCode() & 8 - 1, "f");
        list.set("g".hashCode() & 8 - 1, "g");

        System.out.println("元素集合:" + list);

        System.out.println("获取元素f [\"f\".hashCode() & 8 - 1)] Idx：" + ("f".hashCode() & (8 - 1)) + " 元素：" + list.get("f".hashCode() & 8 - 1));
        System.out.println("获取元素e [\"e\".hashCode() & 8 - 1)] Idx：" + ("e".hashCode() & (8 - 1)) + " 元素：" + list.get("e".hashCode() & 8 - 1));
        System.out.println("获取元素d [\"d\".hashCode() & 8 - 1)] Idx：" + ("d".hashCode() & (8 - 1)) + " 元素：" + list.get("d".hashCode() & 8 - 1));
    }

    @Test
    public void t_ensureCapacity01(){
        final int N = 10000000;
        ArrayList<Integer> list = new ArrayList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        System.out.println("默认情况耗时："+(System.currentTimeMillis() - startTime));
    }

    @Test
    public void t_ensureCapacity02(){
        final int N = 10000000;
        ArrayList<Integer> list = new ArrayList<Integer>(N);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        System.out.println("初始定长容量耗时："+(System.currentTimeMillis() - startTime));
    }

    @Test
    public void t_ensureCapacity03(){
        final int N = 10000000;
        ArrayList<Object> list = new ArrayList<Object>();
        long startTime = System.currentTimeMillis();
        list.ensureCapacity(N);
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        System.out.println("使用ensureCapacity方法耗时："+(System.currentTimeMillis() - startTime));
    }

    @Test
    public void tt(){
        ArrayList<Object> list = new ArrayList<Object>();
        final int N = 10000000;
        for (int i = 0; i < N; i++) {
            list.add(i);
        }

        ArrayList<Object> list2 = new ArrayList<Object>();
        long startTime1 = System.currentTimeMillis();
//        list2.ensureCapacity(N);
        for (int i = 0; i < N; i++) {
            list2.add(i);
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("使用ensureCapacity方法后："+(endTime1 - startTime1));
    }

}
