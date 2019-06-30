package com.roll.casserole.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;

import java.util.Collections;

/**
 * @author zongqiang.hao
 * created on 2018/9/11 下午7:44.
 */
public class ByteBufTest {

    public static void main(String args[]) {
/*
        ByteBuf byteBuf = new EmptyByteBuf(10);
*/
        byte[] a = new byte[1];
        a[0] = -128;
        System.out.println(a[0]);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Byte.MIN_VALUE);
    }
}
