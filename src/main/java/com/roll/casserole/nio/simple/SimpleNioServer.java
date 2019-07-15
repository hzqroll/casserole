package com.roll.casserole.nio.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Set;

/**
 * @author zongqiang.hao
 * created on 2019-07-15 19:53.
 */
public class SimpleNioServer {
    public static void main(String[] args) throws IOException {
        // 打开一个 channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9005));
        serverSocketChannel.configureBlocking(false);

        // 打开一个 selector
        Selector selector = Selector.open();

        // 注册 channel 到 selector 中
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 轮训 selector 中的 channel 事件发生
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeySet) {
                if (selectionKey.isAcceptable()) {
                    // 客户端连接上来的 channel
                    SocketChannel workerChannel = serverSocketChannel.accept();
                    workerChannel.configureBlocking(false);
                    // 把连接的 channel 注册到selector 上面，下次通过 select 唤醒
                    workerChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    // 获取连接上来的 client channel
                    SocketChannel workerChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(10);
                    workerChannel.read(buffer);
                    buffer.flip();
                    // 打印数据
                    System.out.println("received: " + Arrays.toString(buffer.array()));
                    workerChannel.close();
                }
                selectionKeySet.remove(selectionKey);
            }
        }
    }
}
