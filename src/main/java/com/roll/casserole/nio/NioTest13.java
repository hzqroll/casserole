package com.roll.casserole.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @author zongqiang.hao
 * created on 2019-06-24 20:10.
 */
public class NioTest13 {
    public static void main(String[] args) throws IOException {
        String inputFile = "src/main/java/com/roll/casserole/nio/NioTest13_In.txt";
        String outFile = "src/main/java/com/roll/casserole/nio/NioTest13_Out.txt";

        RandomAccessFile inputRandomFile = new RandomAccessFile(inputFile, "r");

        RandomAccessFile outputRandomFile = new RandomAccessFile(outFile, "rw");

        long inputLength = new File(inputFile).length();

        FileChannel inputFileChannel = inputRandomFile.getChannel();
        FileChannel outFileChannel = outputRandomFile.getChannel();

        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);

        Charset charset = Charset.forName("utf-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        CharBuffer charBuffer = decoder.decode(inputData);

        ByteBuffer outData = encoder.encode(charBuffer);


        outFileChannel.write(outData);

        inputRandomFile.close();
        outputRandomFile.close();

    }
}
