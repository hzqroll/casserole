package com.roll.casserole.nio.scalable.reactorcase;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author roll
 * created on 2019-10-28 14:17
 */
@Service
@Scope("prototype")
public class CaseClient {
    private static Charset charset = Charset.forName("utf-8");

    public String client(String message) throws IOException {
        SocketChannel socketChannel = SelectorProvider.provider().openSocketChannel();

        socketChannel.configureBlocking(false);
        Selector selector = SelectorProvider.provider().openSelector();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress(9022));

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                SocketChannel client = (SocketChannel) selectionKey.channel();
                if (selectionKey.isConnectable()) {
                    if (client.isConnectionPending()) {
                        client.finishConnect();

                        String connectMessage = LocalDateTime.now() + " 连接成功, " + message;
                        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                        writeBuffer.put(connectMessage.getBytes());

                        writeBuffer.flip();
                        client.write(writeBuffer);

                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                    }
                } else if (selectionKey.isReadable()) {
                    ByteBuffer receiveMessage = ByteBuffer.allocate(1024);
                    int count = socketChannel.read(receiveMessage);
                    if (count > 0) {
                        receiveMessage.flip();
                        return charset.decode(receiveMessage.asReadOnlyBuffer()).toString();
                    }
                    receiveMessage.clear();
                }
                selectionKeys.clear();
            }
        }
    }
}
