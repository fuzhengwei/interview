package org.itstack.interview;

/**
 * java  -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=dontinline,Singleton.innerSynch -XX:CompileCommand=compileonly,Singleton.innerSynch Singleton
 * java  -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly Singleton
 * java -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly Singleton > sync1
 *
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=dontinline,Singleton.getInstance -XX:CompileCommand=compileonly,Singleton.getInstance
 */
public class Singleton {

    private volatile static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance);
    }

}
