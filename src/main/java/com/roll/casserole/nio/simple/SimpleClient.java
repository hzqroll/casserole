package com.roll.casserole.nio.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author zongqiang.hao
 * created on 2019-07-15 20:22.
 */
public class SimpleClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socket = SocketChannel.open();
        socket.connect(new InetSocketAddress(9004));

        ByteBuffer writeBuffer = ByteBuffer.allocate(10);
        writeBuffer.put((byte) 1);
        writeBuffer.put((byte) 2);
        writeBuffer.flip();
        socket.write(writeBuffer);
    }
}
