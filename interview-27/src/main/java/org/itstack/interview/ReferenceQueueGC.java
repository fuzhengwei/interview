package org.itstack.interview;

import java.lang.ref.ReferenceQueue;

public class ReferenceQueueGC {

    public static void main(String[] args) {
        while (true) {
            ReferenceQueue<String> referenceQueue = new ReferenceQueue<String>();
        }
    }

}
