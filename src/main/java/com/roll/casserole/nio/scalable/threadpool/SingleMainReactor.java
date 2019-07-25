package com.roll.casserole.nio.scalable.threadpool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * 一个reactor，来处理socket的连接，和handler的操作<p></p>
 * <ul>
 * 步骤
 * <li>1.</li>
 * </ul>
 *
 * @author roll
 * created on 2019-07-20 15:44
 */
public class SingleMainReactor implements Runnable {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public SingleMainReactor(int port) throws IOException {
        selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));

        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 在这个selectionKey中附加acceptor事件，其实这个只会在有链接链接上来的时候发生。如果有多个链接发生，这个构造方法会被
        selectionKey.attach(new EasyAcceptor(selectionKey, selector, serverSocketChannel));
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(20000);
                // 阻塞方法
                selector.select();
                // 获取selector 里面的selected keys，刚开始之后accept事件，是绑定了channel的selectionKey
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 分发selectionKey去处理具体io事件
                for (SelectionKey selectionKey : selectionKeys) {
                    new Dispatcher(selectionKey);
                }
                selectionKeys.clear();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        SingleMainReactor singleMainReactor = new SingleMainReactor(9005);
        singleMainReactor.run();
    }
}
