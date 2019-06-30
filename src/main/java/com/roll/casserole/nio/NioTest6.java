package com.roll.casserole.nio;

import java.nio.ByteBuffer;

/**
 * @author zongqiang.hao
 * created on 2019-06-19 19:33.
 */
public class NioTest6 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        buffer.position(2);
        buffer.limit(6);

        ByteBuffer buffer1 = buffer.slice();

        for (int i = 0; i < buffer1.capacity(); i++) {
            byte b = buffer1.get(i);
            b *= 2;
            buffer1.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

    }
}
