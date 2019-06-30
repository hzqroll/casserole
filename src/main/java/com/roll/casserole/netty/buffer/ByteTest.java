package com.roll.casserole.netty.buffer;

import java.nio.ByteBuffer;

/**
 * @author zongqiang.hao
 * created on 2018/9/12 下午10:40.
 */
public class ByteTest {
    public static void main(String args[]){
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        byteBuffer.put((byte) 1);
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.mark());
        System.out.println(byteBuffer.get(0));

        System.out.println("--------------------");
        byteBuffer.put((byte) 2);
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.mark());
        System.out.println(byteBuffer.get());
    }
}
