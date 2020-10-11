package com.roll.casserole.nio.scalable.demo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

/**
 * nio 服务端的入口， 负责分发对应的事件
 * <p>@author roll
 * <p>created on 2020/7/29 9:16 下午
 */
public class NioBootStrap {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    MainAcceptor mainAcceptor = new MainAcceptor();

    SubAcceptor subAcceptor = new SubAcceptor();

    private final ChannelHandler channelHandler;

    public NioBootStrap(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    public void bind(int port) {
        try {
            //serverSocketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(9016));
            serverSocketChannel = SelectorProvider.provider().openServerSocketChannel();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 10090);

            selector = SelectorProvider.provider().openSelector();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                Thread.sleep(500);
                int count = selector.select();
                if (count > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    selectionKeys.forEach(selectionKey -> {
                        if (selectionKey.isAcceptable()) {
                            mainAcceptor.dispatch(selectionKey, selector, channelHandler);
                        } else {
                            subAcceptor.dispatch(selectionKey, selector, channelHandler);
                        }
                    });
                    selectionKeys.clear();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        serverSocketChannel.close();
    }
}
