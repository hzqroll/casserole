package com.roll.casserole.nio.scalable.threadpool;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 处理连接事件
 * 处理accept任务，并且注册读写
 * 启动handler任务
 *
 * @author roll
 * created on 2019-07-22 08:17
 */
public class EasyAcceptor implements Runnable {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;


    public EasyAcceptor(Selector selector, ServerSocketChannel serverSocketChannel) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
    }

    @Override
    public void run() {
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();

            new EasyBlockHandler(selector, socketChannel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
