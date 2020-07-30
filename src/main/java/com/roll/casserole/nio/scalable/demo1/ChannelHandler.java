package com.roll.casserole.nio.scalable.demo1;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * <p>@author roll
 * <p>created on 2020/7/30 2:26 下午
 */
public class ChannelHandler implements Handler {

    public void read(SocketChannel socketChannel) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
        try {
            socketChannel.read(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("收到消息： " + new String(byteBuffer.array()));
    }

    public void write(SocketChannel socketChannel) {
        String message = "当前时间戳" + System.currentTimeMillis();
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(256).put(message.getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connected(SocketChannel socketChannel) {
        System.out.println("连接信息：" + socketChannel.toString());
    }
}
