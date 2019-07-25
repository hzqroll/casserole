package com.roll.casserole.nio.scalable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zongqiang.hao
 * created on 2019-07-09 20:08.
 */
public class SingleReactorServerSocketLoop implements Runnable {
    private final Selector selector;

    private final ServerSocketChannel serverSocketChannel;

    public SingleReactorServerSocketLoop(int port) throws IOException {
        // 打开一个 selector， 准备接受 channel 的注册
        selector = Selector.open();
        //SelectorProvider selectorProvider = SelectorProvider.provider();
        //selector = selectorProvider.openSelector();

        // 打开一个 channel，准备绑定地址和端口
        serverSocketChannel = ServerSocketChannel.open();
        //serverSocketChannel = selectorProvider.openServerSocketChannel();

        // 绑定本地接口
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 设置阻塞状态为非阻塞
        serverSocketChannel.configureBlocking(false);

        // 注册 channel 到 selector 上面，并且设置对 acceptor 事件感兴趣， 返回的selectionKey 标识 serverChannel
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
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
                    // 分发去处理每一个 selectionKey 里面的 IO 事件
                    dispatch(selectionKey);
                }
                // 分发玩之后一定要清除掉，不然下一次还是上一次的 IO 事件
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
    class Acceptor implements Runnable {
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

        boolean inputIsComplete() {
            return true;
        }

        boolean outputIsComplete() {
            return true;
        }

        void process() {
        }

        @Override
        public void run() {
            try {
                if (state == READING) {
                    read();
                } else if (state == SENDING) {
                    send();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        void send() throws IOException {
            socketChannel.read(input);
            state = SENDING;
            selectionKey.interestOps(SelectionKey.OP_WRITE);
        }

        void read() throws IOException {
            socketChannel.write(output);
            selectionKey.cancel();
        }
    }


    // handler with Thread pool, handler的操作在线程池里面进行

    public static void main(String[] args) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress(9004));
        channel.configureBlocking(false);
        Selector selector = Selector.open();

        // 注册 channel 到 selector
        SelectionKey selectionKey1 = channel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int count = selector.select();
            if (count > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = channel.accept();
                        socketChannel.configureBlocking(false);
                        // 把事件再注册到 selector 中，等待感兴趣事件产生
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(100);
                        socketChannel.read(readBuffer);
                        readBuffer.flip();
                        //System.out.println("received: " + readBuffer.get(0));
                    }
                    selectionKeys.remove(selectionKey);
                }
            }
        }
    }
}
