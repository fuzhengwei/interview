package org.itstack.interview.test;

import org.itstack.interview.T;
import org.junit.Test;

import java.nio.charset.Charset;

public class ApiTest {

    @Test
    public void test_00() {
        String str_01 = "abc";
        String str_02 = "abc" + "def";
        String str_03 = str_01 + "def";

        String intern = str_01.intern();

        System.out.println(str_01 == intern);
    }

    @Test
    public void test_000() {
        String str_1 = new String("ab");
        String str_2 = new String("ab");
        String str_3 = "ab";

        System.out.println(str_1 == str_2);
        System.out.println(str_1 == str_2.intern());
        System.out.println(str_1.intern() == str_2.intern());
        System.out.println(str_1 == str_3);
        System.out.println(str_1.intern() == str_3);
    }

    @Test
    public void test_001() {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }

    @Test
    public void test_01() {
        String str_01 = "abc";
        System.out.println("默认方式：" + str_01);

        String str_02 = new String(new char[]{'a', 'b', 'c'});
        System.out.println("char方式：" + str_02);

        String str_03 = new String(new int[]{0x61, 0x62, 0x63}, 0, 3);
        System.out.println("int方式：" + str_03);

        String str_04 = new String(new byte[]{0x61, 0x62, 0x63});
        System.out.println("byte方式：" + str_04);

        char c = str_01.charAt(0);

    }

    @Test
    public void test_02() {
        String s1 = "aaa";
        String s2 = "bbb" + "ccc";
        String s3 = s1 + "bbb";
    }

    @Test
    public void test_03() {
        long startTime = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < 1000000; i++) {
            str += i;
        }
        System.out.println("String 耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
        // 100  1毫秒
        // 1000  6毫秒
        // 10000  389毫秒
        // 50000  7267毫秒
        // 10万 String 耗时：29350毫秒
    }

    @Test
    public void test_03_00() {
        long startTime = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str = new StringBuilder().append(str).append(i).toString();
        }
        System.out.println("String 耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
    }

    @Test
    public void test_04() {
        long startTime = System.currentTimeMillis();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            str.append(i);
        }
        System.out.println("StringBuilder 耗时" + (System.currentTimeMillis() - startTime) + "毫秒");
        // 100  0毫秒
        // 1000  1毫秒
        // 10000 2毫秒
        // 50000 6毫秒
        // 10万 StringBuilder 耗时12毫秒
    }


    @Test
    public void test_05() {
        long startTime = System.currentTimeMillis();
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < 1000000; i++) {
            str.append(i);
        }
        System.out.println("StringBuffer 耗时" + (System.currentTimeMillis() - startTime) + "毫秒");
        // 100  0毫秒
        // 1000  1毫秒
        // 10000 2毫秒
        // 50000 6毫秒
        // 10万 StringBuilder 耗时13毫秒
    }

    @Test
    public void test_06() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("a");
        stringBuilder.append("b");
        stringBuilder.append("c");

        String s = stringBuilder.toString();
    }

    @Test
    public void test_07() {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("a");
        stringBuffer.append("b");
        stringBuffer.append("c");
    }

    @Test
    public void test_08() {

        String str = "abc";

        System.out.println(String.format(str, ""));
        System.out.println(str.hashCode());

    }

}
