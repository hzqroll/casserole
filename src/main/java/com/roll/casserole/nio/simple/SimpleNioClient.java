package com.roll.casserole.nio.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author zongqiang.hao
 * created on 2019-07-15 20:09.
 */
public class SimpleNioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(9005));

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put(1, (byte) 1);
        byteBuffer.put(2, (byte) 2);

        socketChannel.write(byteBuffer);
    }
}
