package com.roll.casserole.nio.scalable.reactorcase.onethread;

import java.nio.channels.*;

/**
 * 连接接收，分发器
 *
 * @author roll
 * created on 2019-10-28 14:20
 */
public class Acceptor {

    private SelectionKey selectionKey;

    private Selector selector;

    public Acceptor(SelectionKey selectionKey, Selector selector) {
        this.selectionKey = selectionKey;
        this.selector = selector;
    }

    public void handleConnection() {
        ServerSocketChannel connectionChannel = (ServerSocketChannel) selectionKey.channel();

        try {
            SocketChannel socketChannel = connectionChannel.accept();
            socketChannel.configureBlocking(false);
            System.out.println("客户端已链接，" + connectionChannel.toString());
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
