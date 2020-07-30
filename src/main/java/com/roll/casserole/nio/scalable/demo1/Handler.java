package com.roll.casserole.nio.scalable.demo1;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * handler处理器
 * <p>@author roll
 * <p>created on 2020/7/29 9:29 下午
 */
public interface Handler {

    /**
     * 处理读事件
     */
    public void read(SocketChannel socketChannel);

    /**
     * 处理写事件
     */
    public void write(SocketChannel socketChannel);

    /**
     * 处理链接事件
     */
    public void connected(SocketChannel socketChannel);
}
