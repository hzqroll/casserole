package com.roll.casserole.nio.scalable.reactorcase.reactorv3.handle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author roll
 * created on 2019-11-05 11:20
 */
public class EventHandlerImpl implements EventHandler {

    private ByteBuffer inputBuffer = ByteBuffer.allocate(1024);

    private ByteBuffer outputBuffer = ByteBuffer.allocate(1024);

    private Charset charset = Charset.forName("utf-8");

    @Override
    public void channelRead(SelectionKey selectionKey) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        int count;
        try {
            count = socketChannel.read(inputBuffer);
            String reviewMessage = "";
            if (count > 0) {
                inputBuffer.flip();
                reviewMessage = charset.decode(inputBuffer.asReadOnlyBuffer()).toString();
                System.out.println("接收客户端发来的消息：" + reviewMessage);
            }
            inputBuffer.clear();

            if (socketChannel.isOpen()) {
                String message = "服务端返回数据。接收到消息 " + reviewMessage;
                outputBuffer.put(message.getBytes(charset));
                outputBuffer.flip();
                socketChannel.write(outputBuffer);
                outputBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
