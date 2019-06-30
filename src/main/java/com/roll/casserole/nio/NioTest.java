package com.roll.casserole.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author zongqiang.hao
 * created on 2019-06-16 16:47.
 */
public class NioTest {
    public static void main(String[] args) {
        // 整数生成随机数并且打印
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            int randNum = new SecureRandom().nextInt(20);
            buffer.put(randNum);
        }

        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

    }
}
