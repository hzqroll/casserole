package com.roll.casserole.nio.scalable.reactorcase.reactorv3;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 接收连接，分发事件
 *
 * @author roll
 * created on 2019-10-29 20:12
 */
public class AcceptorV3 {

    private static final AcceptorV3 instance = new AcceptorV3();

    public static AcceptorV3 getInstance() {
        return instance;
    }

    private EventHandleV3 eventHandler = new EventHandleV3();

    private AcceptorV3() {
    }

    void handleAcceptor(SelectionKey selectionKey, Selector selector) {
        if (selectionKey.isReadable()) {
            eventHandler.handle(selectionKey);
        } else {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);

                ChannelCache.setSelectionKeyMap(selectionKey);
                System.out.println("客户端已连接：" + socketChannel.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
