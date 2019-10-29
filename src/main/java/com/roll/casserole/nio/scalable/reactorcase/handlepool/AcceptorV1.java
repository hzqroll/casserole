package com.roll.casserole.nio.scalable.reactorcase.handlepool;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author roll
 * created on 2019-10-29 20:12
 */
public class AcceptorV1 {
    private static final ExecutorService service = Executors.newSingleThreadExecutor();

    private static final AcceptorV1 instance = new AcceptorV1();

    private static AcceptorV1 getInstance() {
        return instance;
    }

    private AcceptorV1() {
    }

    public void handleAcceptor(SelectionKey selectionKey, Selector selector) {
        service.submit(new AcceptorConnection(selectionKey, selector));
    }

    class AcceptorConnection implements Runnable {

        private SelectionKey selectionKey;

        private Selector selector;

        AcceptorConnection(SelectionKey selectionKey, Selector selector) {
            this.selectionKey = selectionKey;
            this.selector = selector;
        }

        @Override
        public void run() {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                System.out.println("客户端已连接：" + socketChannel.toString());
                socketChannel.register(selector, SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
