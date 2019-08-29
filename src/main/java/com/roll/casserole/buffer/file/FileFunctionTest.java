package com.roll.casserole.buffer.file;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author roll
 * created on 2019-08-27 09:27
 */
public class FileFunctionTest {

    public static void main(String[] args) {
        FileFunctionTest fileFunctionTest = new FileFunctionTest();
        String sourcePath = "/Users/haozongqiang/Downloads/temp/source.txt";
        String destPath = "/Users/haozongqiang/Downloads/temp/dest.txt";
        fileFunctionTest.mappedFileTest(sourcePath, destPath);
        fileFunctionTest.fileInputStreamTest(sourcePath, destPath);
        for (; ; ) {
        }
    }

    private void mappedFileTest(String sourcePath, String destFile) {
        long startTime = System.currentTimeMillis();
        try {
            RandomAccessFile randomAccessFile1 = new RandomAccessFile(sourcePath, "r");
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(destFile, "rw");

            FileChannel fileChannel1 = randomAccessFile1.getChannel();
            FileChannel fileChannel2 = randomAccessFile2.getChannel();

            long size = fileChannel1.size();

            MappedByteBuffer mappedByteBuffer1 = fileChannel1.map(FileChannel.MapMode.READ_ONLY, 0, size);
            MappedByteBuffer mappedByteBuffer2 = fileChannel2.map(FileChannel.MapMode.READ_WRITE, 0, size);

            for (int i = 0; i < size; i++) {
                byte b = mappedByteBuffer1.get(i);
                mappedByteBuffer2.put(i, b);
            }
            mappedByteBuffer2.force();
            randomAccessFile1.close();
            randomAccessFile2.close();
            fileChannel1.close();
            fileChannel2.close();
            System.out.println("MappedFileTest cost: " + (System.currentTimeMillis() - startTime) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fileInputStreamTest(String sourcePath, String destFile) {
        long startTime = System.currentTimeMillis();
        try {
            FileInputStream fileInputStream = new FileInputStream(sourcePath);
            FileOutputStream fileOutputStream = new FileOutputStream(destFile);

            byte[] buf = new byte[8 * 1024];
            int b;
            //in.read(buf,0,buf.length)将读的数据存在buf数组，从0开始到数组长度
            while ((b = fileInputStream.read(buf, 0, buf.length)) != -1) {
                fileOutputStream.write(buf, 0, b);
                fileOutputStream.flush();
            }
            fileInputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("fileInputStreamTest cost: " + (System.currentTimeMillis() - startTime) + " ms");
    }
}
