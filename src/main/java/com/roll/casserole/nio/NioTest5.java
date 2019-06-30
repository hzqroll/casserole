package com.roll.casserole.nio;

import java.nio.ByteBuffer;

/**
 * @author zongqiang.hao
 * created on 2019-06-19 09:04.
 */
public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(15);
        buffer.putLong(600000L);

    }
}
