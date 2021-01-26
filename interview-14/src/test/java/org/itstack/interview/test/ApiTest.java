package org.itstack.interview.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly
 * <p>
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=dontinline,ApiTest.test_volatile
 */
public class ApiTest {



    public static void main(String[] args) {

        Optional.empty();

        final VT vt = new VT();

        Thread Thread01 = new Thread(vt);
        Thread Thread02 = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignore) {
                }
                vt.sign = true;
                System.out.println("vt.sign = true 通知 while (!sign) 结束！");
            }
        });

        Thread01.start();
        Thread02.start();
    }

}

class VT implements Runnable {
    private Logger logger = LoggerFactory.getLogger(ApiTest.class);
    public boolean sign = false;

    public void run() {
        while (true) {
            if (sign) {
                logger.info("你好");
            } else {
                logger.info("你坏");
            }
        }
    }

}
