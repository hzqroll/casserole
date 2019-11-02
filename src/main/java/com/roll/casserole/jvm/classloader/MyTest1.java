package com.roll.casserole.jvm.classloader;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author roll
 * created on 2019-10-13 11:28
 */
public class MyTest1 {
    public static void main(String[] args) {
        Charset charset = Charset.forName("utf-8");

        ByteBuffer byteBuffer1 = ByteBuffer.allocate(100);

        ByteBuffer byteBuffer2 = ByteBuffer.allocate(100);

        //System.out.println(MyChild1.str );
        String a = "hello message哈哈";
        byteBuffer1.put(a.getBytes());
        System.out.println(Arrays.toString(a.getBytes()));

        byteBuffer1.flip();
        String b = charset.decode(byteBuffer1.asReadOnlyBuffer()).toString();
        System.out.println(b);
    }
}


class MyParent1 {
    public static String str = "hello world!";

    static {
        System.out.println("Myparent static block");
    }
}

class MyChild1 extends MyParent1 {

    public static String str2 = "welcome";

    static {
        System.out.println("MyChild static block");
    }
}