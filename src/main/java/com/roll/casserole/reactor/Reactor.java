package com.roll.casserole.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author zongqiang.hao
 * created on 2019-07-06 14:17.
 */
public class Reactor implements Runnable {
    private final Selector selector;

    private final ServerSocketChannel serverSocket;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        // 监听 accept 事件
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        // 绑定 ACCEPT 事件对于的处理单元 Acceptor
        sk.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 阻塞的方法
                selector.select();
                Set selected = selector.selectedKeys();
                // 处理已经 resister 的 selector
                for (Object o : selected) {
                    // 分发事件给相应的处理单元
                    dispatch((SelectionKey) o);
                }
                selected.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey k) {
        Runnable r = (Runnable) k.attachment();
        if (r != null) {
            r.run();
        }
    }

    class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                // 得到的socket，new 一个读时间来处理
                SocketChannel c = serverSocket.accept();
                if (c != null) {
                    new Handler(selector, c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    final class Handler implements Runnable {

        final SocketChannel socket;

        final SelectionKey sk;

        ByteBuffer input = ByteBuffer.allocate(Integer.MAX_VALUE);
        ByteBuffer output = ByteBuffer.allocate(Integer.MAX_VALUE);

        static final int READING = 0, SENDING = 1;
        int state = READING;

        Handler(Selector selector, SocketChannel socketChannel) throws IOException {
            socket = socketChannel;
            socketChannel.configureBlocking(false);
            // 绑定处理本单元对象
            sk = socket.register(selector, 0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
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
            socket.write(output);
            if (outputIsComplete()) {
                sk.channel();
            }
        }

        void read() throws IOException {
            socket.read(input);
            if (inputIsComplete()) {
                process();
                state = SENDING;
                sk.interestOps(SelectionKey.OP_WRITE);
            }
        }
    }
}
