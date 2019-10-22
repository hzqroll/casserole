package com.roll.casserole.nio.channel;

import java.io.*;
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

    public static void main(String[] args) throws IOException {
        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            basic();
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - time));

    }

    public static void general() throws IOException {
        String sourcePath = "/Users/haozongqiang/Downloads/temp/s";
        String descPath = "/Users/haozongqiang/Downloads/temp/dest.txt";

        // 申请buffer空间
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        // 打开源文件
        Path path = Paths.get(sourcePath);
        FileChannel fileChannelSource = FileChannel.open(path, StandardOpenOption.READ);
        //文件内容读到buffer
        fileChannelSource.read(byteBuffer);
        //打开目标文件
        Path path1 = Paths.get(descPath);
        FileChannel fileChannelDesc = FileChannel.open(path1, StandardOpenOption.WRITE);
        //反转
        byteBuffer.flip();
        //buffer内容读到文件里面
        fileChannelDesc.write(byteBuffer);
        //finalize操作
        fileChannelSource.close();
        fileChannelDesc.force(true);
        fileChannelDesc.close();
    }

    public static void inputStream() throws IOException {
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

    public static void basic() throws IOException {
        String si = "/Users/haozongqiang/Downloads/temp/sb";
        String di = "/Users/haozongqiang/Downloads/temp/db.txt";

        FileInputStream fileInputStream = new FileInputStream(new File(si));

        FileOutputStream fileOutputStream = new FileOutputStream(new File(di));

        byte[] data = new byte[1024];
        fileInputStream.read(data);

        fileOutputStream.write(data);
        fileInputStream.close();
        fileOutputStream.close();
    }
}
