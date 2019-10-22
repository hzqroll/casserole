package com.roll.casserole.nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author roll
 * created on 2019-10-18 10:53
 */
public class FileChannelTest2 {
    public static void main(String[] args) throws IOException {
        String si = "/Users/haozongqiang/Downloads/temp/si";
        String di = "/Users/haozongqiang/Downloads/temp/di.txt";

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        File sfile = new File(si);
        FileInputStream sfileInputStream = new FileInputStream(sfile);

        FileChannel sFileChannel = sfileInputStream.getChannel();
        sFileChannel.read(byteBuffer);

        File dfile = new File(di);
        FileOutputStream dfileInputStream = new FileOutputStream(dfile);
        FileChannel dFileChannel = dfileInputStream.getChannel();
        byteBuffer.flip();
        //dFileChannel.write(byteBuffer);
        sFileChannel.transferTo(0, sFileChannel.size(), dFileChannel);

        dFileChannel.force(true);
        sfileInputStream.close();
        dfileInputStream.close();
        sFileChannel.close();
        dFileChannel.close();
    }
}
