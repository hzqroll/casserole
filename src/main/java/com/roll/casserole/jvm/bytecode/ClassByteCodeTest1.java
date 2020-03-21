package com.roll.casserole.jvm.bytecode;

/**
 * @author roll
 * created on 2020/3/18 9:02 上午
 */
public class ClassByteCodeTest1 {
    private String str = "hello";

    private int i = 1;

    private static Integer i1 = 1000;

    private Long aLong = 123L;

    public long aLong1 = 1L;

    private byte[] bytes;

    public Object object = new Object();

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public synchronized void print(String a) {
        synchronized (object) {
            System.out.println(a);
        }
    }

    public String returnStr() {
        return "world";
    }

    public static void main(String[] args) {
        ClassByteCodeTest1 classByteCodeTest = new ClassByteCodeTest1();
        classByteCodeTest.setBytes(new byte[1]);

        i1 = 20;
    }
}
