package com.roll.casserole.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zongqiang.hao
 * created on 2019-06-16 18:17.
 */
public class NioTest3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/roll/casserole/casserole/src/main/java/com/roll/casserole/nio/NioTest3.txt");

        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] message = "hello world wolcome,nihao".getBytes();

        for (int i = 0; i < message.length; i++) {
            byteBuffer.put(message[i]);
        }

        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileChannel.close();
    }
}
