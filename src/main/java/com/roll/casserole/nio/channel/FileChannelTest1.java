package com.roll.casserole.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author roll
 * created on 2019-10-17 20:51
 */
public class FileChannelTest1 {

    private static final String sourcePath = "/Users/haozongqiang/Downloads/temp/source.txt";

    private static final String descPath = "/Users/haozongqiang/Downloads/temp/dest.txt";

    public static void main(String[] args) throws IOException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        Path path = Paths.get(sourcePath);
        FileChannel fileChannelSource = FileChannel.open(path, StandardOpenOption.READ);

        fileChannelSource.read(byteBuffer);

        Path path1 = Paths.get(descPath);
        FileChannel fileChannelDesc = FileChannel.open(path1, StandardOpenOption.WRITE);
        System.out.println(byteBuffer);

        System.out.println(byteBuffer.getInt());
        byte[] message = "hello world wolcome,nihao".getBytes();
/*
        for (int i = 0; i < message.length; i++) {
            byteBuffer.put(message[i]);
        }*/
        byteBuffer.flip();

        fileChannelDesc.write(byteBuffer);
        fileChannelSource.close();
        fileChannelDesc.force(true);
        fileChannelDesc.close();

    }
}
