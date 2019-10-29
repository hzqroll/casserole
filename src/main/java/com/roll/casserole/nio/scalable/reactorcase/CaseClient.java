package com.roll.casserole.nio.scalable.reactorcase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author roll
 * created on 2019-10-28 14:17
 */
public class CaseClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SelectorProvider.provider().openSocketChannel();

        socketChannel.connect(new InetSocketAddress(9021));

        for (; ; ) {
            String msg = new BufferedReader(new InputStreamReader(System.in)).readLine();
            ByteBuffer writeBuffer = ByteBuffer.allocate(10);
            writeBuffer.put(msg.getBytes());
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
        }
    }
}
