package org.itstack.interview;

public class Bean_B {

    private Bean_A bean_a;

    public Bean_A getBean_a() {
        return bean_a;
    }

    public void setBean_a(Bean_A bean_a) {
        this.bean_a = bean_a;
    }

    public void sayHello(String name) {
        System.out.println("hello, " + name);
    }
}
