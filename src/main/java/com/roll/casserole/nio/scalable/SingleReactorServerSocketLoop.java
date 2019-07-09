package com.roll.casserole.nio.scalable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

/**
 * @author zongqiang.hao
 * created on 2019-07-09 20:08.
 */
public class SingleReactorServerSocketLoop implements Runnable {
    final Selector selector;

    final ServerSocketChannel serverSocketChannel;

    public SingleReactorServerSocketLoop(int port) throws IOException {
        selector = Selector.open();
        //SelectorProvider selectorProvider = SelectorProvider.provider();
        //selector = selectorProvider.openSelector();

        serverSocketChannel = ServerSocketChannel.open();
        //serverSocketChannel = selectorProvider.openServerSocketChannel();

        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);

        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    // dispatch
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                //Selects a set of keys whose corresponding channels are ready for I/O operations. 阻塞
                int SelectorCount = selector.select();
                // Returns this selector's selected-key set. 可以被移除， 但是不能手工增加
                Set<SelectionKey> selected = selector.selectedKeys();

                for (SelectionKey selectionKey : selected) {

                }
                selected.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 分发
    void dispatch(SelectionKey selectionKey) {
        // handler 塞进去的是一个任务
        Runnable runnable = (Runnable) selectionKey.attachment();
        if (runnable != null) {
            runnable.run();
        }
    }

    // acceptor, SelectionKey，绑定 channel，分发 channel 中的任务
    class Accpetor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    new Handler(selector, socketChannel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    final class Handler implements Runnable {
        final SocketChannel socketChannel;
        final SelectionKey selectionKey;
        ByteBuffer input = ByteBuffer.allocate(100);
        ByteBuffer output = ByteBuffer.allocate(100);
        static final int READING = 0;
        static final int SENDING = 1;
        int state = READING;

        public Handler(Selector selector, SocketChannel socketChannel) throws Exception {
            this.socketChannel = socketChannel;
            socketChannel.configureBlocking(false);

            selectionKey = this.socketChannel.register(selector, 0);
            selectionKey.attach(this);
            selectionKey.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }

        @Override
        public void run() {

        }
    }

}
