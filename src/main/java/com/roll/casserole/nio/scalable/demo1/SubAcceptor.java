package com.roll.casserole.nio.scalable.demo1;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;

import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 处理read 和 write 事件
 * <p>@author roll
 * <p>created on 2020/7/30 2:21 下午
 */
public class SubAcceptor implements Acceptor {

    @Override
    public void dispatch(SelectionKey selectionKey, Selector selector, ChannelHandler channelHandler) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        try {
            if (selectionKey.isReadable()) {
                channelHandler.read(socketChannel);
                socketChannel.register(selector, SelectionKey.OP_WRITE);
            }
            if (selectionKey.isWritable()) {
                channelHandler.write(socketChannel);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }
}
