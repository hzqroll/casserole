package com.roll.casserole.nio;

import java.nio.ByteBuffer;

/**
 * 只读 buffer
 *
 * @author zongqiang.hao
 * created on 2019-06-19 19:41.
 */
public class NioTest7 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println(buffer.getClass());
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

    }
}
