package org.itstack.interview;

/**
 * java -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=dontinline,LockExample.innerSynch -XX:CompileCommand=compileonly,LockExample.innerSynch
 */
public class LockExample {

    private static int i;
    private static int j;
    private static int k;

    private static volatile int state;

    public static void main(String[] args) {
        LockExample lockExample = new LockExample();
        lockExample.innerSynch();
    }

    public LockExample() {
        i = 2;
        state = 1;
    }

    public void innerSynch() {
        synchronized (this) {
            System.out.println("[ get inner lock ] success");
            i++;
            j++;
            state++;
            k++;
            try {
                this.wait();
            } catch (InterruptedException ex) {

            }
            notifyAll();
            System.out.println("[ notifyAll ] ... ...");
        }

    }


}
