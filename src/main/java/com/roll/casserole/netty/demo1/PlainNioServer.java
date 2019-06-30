package com.roll.casserole.netty.demo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO实例
 * @author zongqiang.hao
 * created on 2019-04-16 21:44.
 */
public class PlainNioServer {
    public void server(int port) throws IOException {
        //绑定服务器到指定的端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket ss = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        //绑定服务器到指定端口
        ss.bind(address);
        //打开selector处理channel
        Selector selector = Selector.open();
        //注册serverSocket到ServerSocket,并指定这是专门接受连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("HI!\r\n".getBytes());
        for (; ; ) {
            try {
                //等待新的事件来处理,浙江阻塞,直到一个实践的传入
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            //从收到的事件中,获取selectionKey的实例
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    //检查该事件是一个新的连接准备好接受
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        //接受客户端,并用selector注册
                        client.register(selector, SelectionKey.OP_ACCEPT | SelectionKey.OP_READ, msg.duplicate());
                    }
                    //检查socket是否准备好写数据
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        //将数据写入到所连接的客户端,如果网络饱和,连接是可写的,name这个循环将写入数据,知道该缓冲区是空的
                        while (buffer.hasRemaining()) {
                            if (client.write(buffer) == 0) {
                                break;
                            }
                        }
                    }

                } catch (IOException ex) {
                    //关闭连接
                    key.cancel();
                    key.channel().close();
                }
            }
        }
    }
}
