package org.itstack.interview;

/**
 * testGC()方法执行后， objA和objB会不会被GC呢？
 *
 * -XX:+PrintGCDetails  打印每次gc的回收情况 程序运行结束后打印堆空间内存信息(包含内存溢出的情况)
 * -XX:+PrintHeapAtGC  打印每次gc前后的内存情况
 * -XX:+PrintGCTimeStamps 打印每次gc的间隔的时间戳 full gc为每次对新生代老年代以及整个空间做统一的回收 系统中应该尽量避免
 * -XX:+TraceClassLoading  打印类加载情况
 * -XX:+PrintClassHistogram 打印每个类的实例的内存占用情况
 * -Xloggc:/Users/xiaofuge/Desktop/logs/log.log  配合上面的使用将上面的日志打印到指定文件
 * -XX：HeapDumpOnOutOfMemoryError 发生内存溢出将堆信息转存起来 以便分析
 *
 * Xms   默认情况下堆内存的64分之一
 * -Xmx   默认情况下对内存的4分之一
 * -Xmn   默认情况下堆内存的64分之一
 * -XX:NewRatio  默认为2
 * -XX:SurvivorRatio 默认为8
 * jdk永久代大小设置 -XX:PermSize=64M -XX:MaxPermSize=128M
 */
public class ReferenceCountingGC {

    public Object instance = null;
    private static final int _1MB = 1024 * 1024;
    /**
     * 这个成员属性的唯一意义就是占点内存， 以便能在GC日志中看清楚是否有回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) throws InterruptedException {
        testGC();
    }

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        // 假设在这行发生GC， objA和objB是否能被回收？
        System.gc();
    }

}
