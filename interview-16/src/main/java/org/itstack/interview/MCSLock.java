package org.itstack.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MCSLock implements Lock {

    private AtomicReference<MCSLock.Node> tail = new AtomicReference<>(null);
    ;
    private ThreadLocal<MCSLock.Node> node;

    private static class Node {
        private volatile boolean locked = false;
        private volatile Node next = null;
    }

    public MCSLock() {
        node = ThreadLocal.withInitial(Node::new);
    }

    @Override
    public void lock() {
        Node node = this.node.get();
        Node preNode = tail.getAndSet(node);
        if (null == preNode) {
            node.locked = true;
            return;
        }
        node.locked = false;
        preNode.next = node;
        while (!node.locked) ;
    }

    @Override
    public void unlock() {
        Node node = this.node.get();
        if (null != node.next) {
            node.next.locked = true;
            node.next = null;
            return;
        }
        if (tail.compareAndSet(node, null)) {
            return;
        }
        while (node.next == null) ;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }
}
