package com.roll.casserole.nio.scalable.reactorcase.reactorv3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author roll
 * created on 2019-10-28 14:48
 */
public class MainReactorV3 {
    public static void main(String[] args) throws IOException {

        Selector selector = SelectorProvider.provider().openSelector();

        ServerSocketChannel serverSocketChannel = SelectorProvider.provider().openServerSocketChannel();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        serverSocketChannel.bind(new InetSocketAddress(9022));

        while (true) {
            int count = selector.select();
            if (count > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
                while (selectionKeyIterator.hasNext()) {
                    final SelectionKey selectionKey = selectionKeyIterator.next();
                    selectionKeyIterator.remove();
                    AcceptorV3.getInstance().handleAcceptor(selectionKey, selector);
                }
                selectionKeys.clear();
            }
        }
    }
}
