package com.roll.casserole.netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author haozq
 * Date: 2018/8/11 下午3:21
 */
public class EchoServer {
	private final int port;

	public EchoServer(int port) {
		this.port = port;
	}

	public static void main(String args[]) throws Exception {
		if (args.length != 1) {
			System.out.println("Usage: " + EchoServer.class.getSimpleName() + "<port>");
		}
		int port = Integer.parseInt(args[0]);
		new EchoServer(port).start();
	}

	public void start() throws Exception {
		final EchoServerhandler serverhandler = new EchoServerhandler();
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(group)
					.channel(NioServerSocketChannel.class)
					.localAddress(new InetSocketAddress(port))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(serverhandler);
						}
					});
			//异步的绑定服务器，滴啊用sync方法阻塞等待绑定完成
			ChannelFuture future = bootstrap.bind().sync();
			//获取channel的closeFuture，并阻塞当前线程知道它完成
			future.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
}
