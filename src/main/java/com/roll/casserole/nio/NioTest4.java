package com.roll.casserole.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zongqiang.hao
 * created on 2019-06-19 08:34.
 */
public class NioTest4 {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("src/main/java/com/roll/casserole/nio/input.txt");
        FileOutputStream outputStream = new FileOutputStream("/Users/roll/casserole/casserole/src/main/java/com/roll/casserole/nio/output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();


        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            byteBuffer.clear();
            int read = inputChannel.read(byteBuffer);
            if (-1 == read) {
                break;
            }
            byteBuffer.flip();
            outputChannel.write(byteBuffer);
        }
        inputChannel.close();
        outputChannel.close();
    }
}
