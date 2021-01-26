package org.itstack.interview;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {

    public static void main(String[] args) {
        String base = "string";
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }

}
