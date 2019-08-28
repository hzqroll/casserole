package com.roll.casserole.buffer;

import io.netty.buffer.ByteBuf;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author roll
 * created on 2019-08-25 19:15
 */
public class FdTest {
    public static void main(String[] args) throws IOException {
        /*FileOutputStream fileOutputStream = new FileOutputStream(FileDescriptor.out);
        // 输出: 你好
        fileOutputStream.write("你好".getBytes());
        fileOutputStream.close();

        System.out.println("你好");*/

        String path = "/Users/haozongqiang/Downloads/temp/time.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        fileChannel.read(byteBuffer);
        System.out.println(byteBuffer);
        fileChannel.close();
        fileOutputStream.close();
    }

}
