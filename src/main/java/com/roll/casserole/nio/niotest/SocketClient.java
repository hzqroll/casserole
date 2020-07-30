package com.roll.casserole.nio.niotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

/**
 * <p>@author roll
 * <p>created on 2020/7/28 8:10 上午
 */
public class SocketClient {
    public static void main(String[] args) {
        client2();
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
            String clientName = "客户端1请求链接";
            SocketChannel socketChannel = SelectorProvider.provider().openSocketChannel();
            socketChannel.connect(new InetSocketAddress(9016));
            Selector selector = SelectorProvider.provider().openSelector();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_WRITE);
            while (true) {
                int count = selector.select();
                if (count > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    for (SelectionKey selectionKey : selectionKeys) {
                        if (selectionKey.isWritable()) {
                            ByteBuffer message = ByteBuffer.allocate(256);
                            message.put((clientName + System.currentTimeMillis()).getBytes());
                            message.flip();
                            socketChannel.write(message);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        } else if (selectionKey.isReadable()) {
                            ByteBuffer message = ByteBuffer.allocate(256);
                            socketChannel.read(message);
                            message.flip();
                            System.out.println(clientName + ", 收到来自服务端的消息， " + new String(message.array()));
                            socketChannel.register(selector, SelectionKey.OP_WRITE);
                        }
                    }
                    selectionKeys.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
