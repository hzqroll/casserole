package com.roll.casserole.netty;

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
 * 未使用netty的异步网络编程
 *
 * @author haozq
 * Date: 2018/8/12 下午12:28
 */
public class PlainNioServer {
	public void server(int port) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		ServerSocket ssocket = serverSocketChannel.socket();
		InetSocketAddress address = new InetSocketAddress(port);
		ssocket.bind(address);//将服务器绑定到选定的端口
		//打开Selector来处理channel
		Selector selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//将ServerSocket注册到Sekector已接受连接

		final ByteBuffer msg = ByteBuffer.wrap("HI!\r\n".getBytes());
		for (; ; ) {
			try {
				//等到需要处理的新事件，阻塞将一直持续到下一个传入事件
				selector.select();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			//获取所哟的接受事件的key实例
			Set<SelectionKey> readyKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = readyKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();
				try {
					//检查事件是否是一个新的一届就绪的可以被接受的心裂
					if (key.isAcceptable()) {
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel client = server.accept();
						client.configureBlocking(false);
						//接受客户端，并将它注册到选择器
						client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
						System.out.println("Accept connetcion from " + client);
						if (key.isWritable()) {//检查套接字是否已经准备好写数据
							SocketChannel socketChannel = (SocketChannel) key.channel();
							ByteBuffer buffer = (ByteBuffer) key.attachment();
							while (buffer.hasRemaining()) {
								//将数据写到已连接的客户端
								if (client.write(buffer) == 0) {
									break;
								}
							}
							client.close();
						}
					}
				} catch (IOException e) {
					key.channel();
					try {
						key.channel().close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}
}
