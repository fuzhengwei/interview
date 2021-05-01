package org.itstack.interview;

public class Bean_A {

    private Bean_B bean_b;

    public Bean_B getBean_b() {
        return bean_b;
    }

    public void setBean_b(Bean_B bean_b) {
        this.bean_b = bean_b;
    }

    public void sayHello(String name) {
        System.out.println("hello, " + name);
    }
}
