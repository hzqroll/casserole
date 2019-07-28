package com.roll.casserole.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author zongqiang.hao
 * created on 2019-06-24 20:11.
 */
public class NioServer {
    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        // 获取服务器端的socket对象
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try {
                // 返回所关注的事件数量,否则一直阻塞
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {

                    final SocketChannel client;

                    try {
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            client = server.accept();

                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);

                            String key = "[" + UUID.randomUUID().toString() + "]";
                            clientMap.put(key, client);
                        } else if (selectionKey.isReadable()) {
                            client = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(215);
                            int count = client.read(byteBuffer);
                            if (count > 0) {
                                byteBuffer.flip();
                                Charset charset = Charset.forName("utf-8");
                                String reviewMessage = String.valueOf(charset.decode(byteBuffer));

                                System.out.println(client + ": " + reviewMessage);

                                String senderKey = null;
                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    if (client == entry.getValue()) {
                                        senderKey = entry.getKey();
                                        break;
                                    }
                                }
                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    SocketChannel socketChannel = entry.getValue();
                                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                    writeBuffer.put((senderKey + ": " + reviewMessage).getBytes());

                                    writeBuffer.flip();
                                    socketChannel.write(writeBuffer);
                                }
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                selectionKeys.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
