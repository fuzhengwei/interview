package org.itstack.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 1.初始状态 tail指向一个node(head)节点
 * +------+
 * | head | <---- tail
 * +------+
 *
 * 2.lock-thread加入等待队列: tail指向新的Node，同时Prev指向tail之前指向的节点
 * +----------+
 * | Thread-A |
 * | := Node  | <---- tail
 * | := Prev  | -----> +------+
 * +----------+        | head |
 *                     +------+
 *
 *             +----------+            +----------+
 *             | Thread-B |            | Thread-A |
 * tail ---->  | := Node  |     -->    | := Node  |
 *             | := Prev  | ----|      | := Prev  | ----->  +------+
 *             +----------+            +----------+         | head |
 *                                                          +------+
 * 3.寻找当前node的prev-node然后开始自旋
 *
 */
public class CLHLock implements Lock {

    private final ThreadLocal<CLHLock.Node> prev;
    private final ThreadLocal<CLHLock.Node> node;
    private final AtomicReference<CLHLock.Node> tail = new AtomicReference<>(new CLHLock.Node());

    private static class Node {
        private volatile boolean locked;
    }

    public CLHLock() {
        this.prev = ThreadLocal.withInitial(() -> null);
        this.node = ThreadLocal.withInitial(CLHLock.Node::new);
    }

    @Override
    public void lock() {
        final Node node = this.node.get();
        node.locked = true;
        Node pred_node = this.tail.getAndSet(node);
        this.prev.set(pred_node);
        // 自旋
        while (pred_node.locked);
    }

    @Override
    public void unlock() {
        final Node node = this.node.get();
        node.locked = false;
        this.node.set(this.prev.get());
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
