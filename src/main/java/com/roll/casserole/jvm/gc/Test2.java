package com.roll.casserole.jvm.gc;

/**
 * 虚拟机栈溢出demo
 *  -Xss200k 设置栈大小200k，（最小是160k）
 * <p>@author roll
 * <p>created on 2020/8/9 10:48 上午
 */
public class Test2 {
    private int length;

    public int getLength() {
        return length;
    }

    public void test() {
        this.length++;
        test();
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        try {
            test2.test();
        } catch (Throwable throwable) {
            System.out.println(test2.getLength());
            throwable.printStackTrace();
        }
    }
}
