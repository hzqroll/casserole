package com.roll.casserole.nio.scalable.demo1;

import java.io.IOException;
import java.nio.channels.*;

/**
 * 处理链接事件
 * <p>@author roll
 * <p>created on 2020/7/30 2:21 下午
 */
public class MainAcceptor implements Acceptor {

    @Override
    public void dispatch(SelectionKey selectionKey, Selector selector, ChannelHandler channelHandler) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            channelHandler.connected(socketChannel);
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
