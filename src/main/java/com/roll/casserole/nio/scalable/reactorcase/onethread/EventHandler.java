package com.roll.casserole.nio.scalable.reactorcase.onethread;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @author roll
 * created on 2019-10-28 14:40
 */
public class EventHandler {
    private SelectionKey selectionKey;

    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public EventHandler(SelectionKey selectionKey) {
        this.selectionKey = selectionKey;
    }

    public void handle() {

        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        try {
            socketChannel.read(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(byteBuffer.toString());
    }
}
