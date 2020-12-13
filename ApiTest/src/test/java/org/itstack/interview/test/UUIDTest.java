package org.itstack.interview.test;

import org.junit.Test;

import java.util.Random;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack) @2020
 */
public class UUIDTest {

    @Test
    public void test_01() {
        int codeId = RedisUtil.incr("codeUUID");
        String UUID = String.format("%08d", codeId);
        System.out.println(UUID);
    }

    @Test
    public void test_02() {
        Random random = new Random();
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            int number = random.nextInt(3);
            switch (number) {
                case 0:
                    code.append((char) (random.nextInt(26) + 65)); // 65 ~ 90
                    break;
                case 1:
                    code.append((char) (random.nextInt(26) + 97)); // 97 ~ 122
                    break;
                case 2:
                    code.append((char) (random.nextInt(9) + 48)); // 48 ~ 97
                    break;
            }
        }
        System.out.println(code.toString());
    }

    final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
            'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z', '0', '1', };

    public static void main(String[] args) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        System.out.println(idWorker.nextId());

        long code = idWorker.nextId();

        char[] buf = new char[64];
        int charPos = 64;
        int radix = 1 << 6;
        long mask = radix - 1;
        do {
            buf[--charPos] = digits[(int) (code & mask)];
            code >>>= 6;
        } while (code != 0);

        System.out.println(new String(buf, charPos, (64 - charPos)));

    }

}
