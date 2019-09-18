package com.roll.casserole.buffer;

import java.nio.ByteBuffer;

/**
 * @author roll
 * created on 2019-09-17 16:56
 */
public class DecodeTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 0; i < 8; i++) {
            if ((i % 2) == 0) {
                byteBuffer.put((byte) 0);
            } else {
                byteBuffer.put((byte) 1);
            }
        }
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            System.out.println(byteBuffer.get());
        }
        byteBuffer.flip();
        byteBuffer.limit(8);
        int a = byteBuffer.getInt();
        System.out.println(a);
    }
}
