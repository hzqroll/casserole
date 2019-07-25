package com.roll.casserole.nio.scalable.threadpool;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;

/**
 * @author roll
 * created on 2019-07-21 20:41
 */
public class EasyBlockHandler implements Runnable {

    private SocketChannel socketChannel;
    private SelectionKey selectionKey;

    public EasyBlockHandler(Selector selector, SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        this.socketChannel.configureBlocking(false);

        selectionKey = this.socketChannel.register(selector, 0);
        selectionKey.attach(this);
        if (!selectionKey.isReadable()) {
            selectionKey.interestOps(SelectionKey.OP_READ);
        }
        selector.wakeup();
    }

    @Override
    public void run() {
        if (selectionKey.isWritable()) {
            System.out.println("send");
        } else if (selectionKey.isReadable()) {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            try {
                socketChannel.read(buffer);
                buffer.flip();
                // 打印数据
                System.out.println("received: " + Arrays.toString(buffer.array()));
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
