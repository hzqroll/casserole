package com.roll.casserole.buffer;

import java.nio.ByteBuffer;

/**
 * @author roll
 * created on 2019-08-29 17:40
 */
public class AnotMemoTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024);
            while (byteBuffer.hasRemaining()) {
                byteBuffer.put((byte) 1);
            }
        }
        for (;;){

        }
    }
}
