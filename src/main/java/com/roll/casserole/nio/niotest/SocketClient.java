package com.roll.casserole.nio.niotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * <p>@author roll
 * <p>created on 2020/7/28 8:10 上午
 */
public class SocketClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        client1();
    }

    public static void client1() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(9016));
        while (true) {
            String message = String.valueOf(System.currentTimeMillis());
            try {
                socket.getOutputStream().write(message.getBytes());
                Thread.sleep(100);
                byte[] result = new byte[256];
                int length = socket.getInputStream().read(result);
                System.out.println(new String(result, 0, length));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void client2() {
        try {
            SocketChannel socketChannel = SelectorProvider.provider().openSocketChannel();
            socketChannel.connect(new InetSocketAddress(9015));

            Selector selector = SelectorProvider.provider().openSelector();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
