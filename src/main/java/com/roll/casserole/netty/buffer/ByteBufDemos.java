package com.roll.casserole.netty.buffer;

import io.netty.buffer.*;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author zongqiang.hao
 * created on 2018/10/21 上午11:39.
 */
public class ByteBufDemos {

    public static void main(String args[]) {
        ByteBufDemos byteBufcasserole = new ByteBufDemos();
        //byteBufcasserole.heapBuffer();
        byteBufcasserole.sliceBuffer();
    }

    /**
     * 堆内存模式
     */
    public void heapBuffer() {
        ByteBuf heapBuf = ByteBufAllocator.DEFAULT.heapBuffer(99);
        heapBuf.capacity(99);
        for (int i = 0; i < 100; i++) {
            heapBuf.writeByte((byte) i);
        }
        System.out.println("offset: " + heapBuf.arrayOffset());
        System.out.println("readerIndex: " + heapBuf.readerIndex());
        System.out.println("writerIndex: " + heapBuf.writerIndex());
        heapBuf.readByte();
        byte[] array = heapBuf.array();
        System.out.println("offset: " + heapBuf.arrayOffset());
        System.out.println("readerIndex: " + heapBuf.readerIndex());
        System.out.println("writerIndex: " + heapBuf.writerIndex());
        System.out.println("array: " + Arrays.toString(array));
    }

    public void sliceBuffer() {
        Charset charset = Charset.forName("utf-8");
        ByteBuf byteBuf = Unpooled.copiedBuffer("copy casserole", charset);
        ByteBuf copy = byteBuf.copy(0, 6);
        System.out.println(byteBuf.toString(charset));
        byteBuf.setByte(0, (byte) 'a');
        System.out.println(byteBuf.getByte(0));
        System.out.println(copy.getByte(0));

        ByteBuf sliceBuf = byteBuf.slice(0, 6);
        byteBuf.setByte(0, (byte) 'b');
        System.out.println(byteBuf.getChar(0));
        System.out.println(sliceBuf.getChar(0));
    }

    public void setAndGet(){

    }
}
