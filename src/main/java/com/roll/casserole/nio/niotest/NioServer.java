package com.roll.casserole.nio.niotest;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

/**
 * <p>@author roll
 * <p>created on 2020/7/28 8:11 上午
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        server2();
    }

    public static void server1() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(9015));
            new Thread(() -> {
                while (true) {
                    Socket socket = null;
                    try {
                        socket = serverSocket.accept();
                        InputStream inputStream = socket.getInputStream();
                        byte[] datas = new byte[256];
                        int length = inputStream.read(datas);
                        System.out.println("接收到数据：" + new String(datas, 0, length));
                        socket.getOutputStream().write("服务端已经接收到消息".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void server2() {
        try {
            Selector selector = SelectorProvider.provider().openSelector();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(9016));
            serverSocketChannel.configureBlocking(false);
            // 注册链接事件类型
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int count = selector.select();
                if (count > 0) {
                    Set<SelectionKey> selectorSet = selector.selectedKeys();
                    selectorSet.forEach(selectionKey -> {
                        if (selectionKey.isReadable()) {
                            // 读取数据
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                            try {
                                socketChannel.read(byteBuffer);
                                byteBuffer.flip();
                                System.out.println("服务端接收到数据： " + new String(byteBuffer.array()) + "当前时间戳：" + System.currentTimeMillis());
                                socketChannel.register(selector, SelectionKey.OP_WRITE);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else if (selectionKey.isAcceptable()) {
                            ServerSocketChannel serverSocketChannelAccept = (ServerSocketChannel) selectionKey.channel();
                            try {
                                SocketChannel socketChannel = serverSocketChannelAccept.accept();
                                ByteBuffer byteBufferRead = ByteBuffer.allocate(256);
                                socketChannel.read(byteBufferRead);
                                System.out.println("服务端接收到链接：Channel：" + socketChannel + ", 附加消息： " + new String(byteBufferRead.array()));
                                // 发送消息
                                ByteBuffer byteBuffer;
                                byteBuffer = ByteBuffer.wrap(("服务端已连接" + System.currentTimeMillis()).getBytes());
                                socketChannel.write(byteBuffer);
                                socketChannel.configureBlocking(false);
                                socketChannel.register(selector, SelectionKey.OP_READ);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else if (selectionKey.isWritable()) {
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            try {
                                String message = "来自服务端的消息，时间戳" + System.currentTimeMillis();
                                ByteBuffer msg = ByteBuffer.allocate(256);
                                msg.put(message.getBytes());
                                msg.flip();
                                socketChannel.write(msg);
                                socketChannel.register(selector, SelectionKey.OP_READ);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    selectorSet.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
