package com.roll.casserole.nio.scalable.reactorcase.handlepool;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author roll
 * created on 2019-10-29 20:39
 */
public class EventHandleV1 {

    private Charset charset = Charset.forName("utf-8");

    private static final ExecutorService handlerService = Executors.newFixedThreadPool(100);

    private int state = STATE_WRITE;

    private static final int STATE_READ = 1;

    private static final int STATE_WRITE = 2;

    public void handle(Selector selector, SelectionKey selectionKey) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        if (selectionKey.isReadable()) {
            state = STATE_READ;
            try {
                socketChannel.register(selector, SelectionKey.OP_WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            state = STATE_WRITE;
            selectionKey.interestOps(SelectionKey.OP_READ);
        }
        handlerService.execute(new EventHandlerTask(socketChannel));
    }

    class EventHandlerTask implements Runnable {

        private SocketChannel socketChannel;

        private ByteBuffer inputBuffer = ByteBuffer.allocate(1024);

        private ByteBuffer outputBuffer = ByteBuffer.allocate(1024);

        public EventHandlerTask(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void run() {
            if (state == STATE_READ) {
                try {
                    inputBuffer.clear();
                    int count = socketChannel.read(inputBuffer);
                    if (count > 0) {
                        inputBuffer.flip();
                        String reviewMessage = charset.decode(inputBuffer.asReadOnlyBuffer()).toString();
                        System.out.println("接收客户端发来的消息：" + reviewMessage);
                    }
                    inputBuffer.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    if (socketChannel.isOpen()) {
                        outputBuffer.put("服务端返回数据。".getBytes(charset));
                        outputBuffer.flip();
                        socketChannel.write(outputBuffer);
                        outputBuffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
