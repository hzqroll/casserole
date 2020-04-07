package com.roll.casserole.jvm.bytecode;

/**
 * @author zongqiang.hao
 * created on 2020/3/23 9:26 下午.
 */
public class InvokeVirtualTest {
    public static void main(String[] args) {
        Father son = new Son();
        son.hi();
    }
}

class Father {
    public Father() {
        System.out.println("i am father");
        System.out.println(this);
        hi();
        hello();
    }

    public void hi() {
        System.out.println("father say hi");
    }

    private void hello() {
        System.out.println("father say hello");
    }
}

class Son extends Father {

    public void hi() {
        System.out.println("son say hi");
    }
}
