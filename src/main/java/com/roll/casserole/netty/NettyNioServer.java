package com.roll.casserole.netty;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author haozq
 * Date: 2018/8/12 下午1:43
 */
public class NettyNioServer {
	public void server(int port) throws InterruptedException {
		final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi! \r\n", Charset.forName("UTF-8")));
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			//创建ServerBootstrap
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
					.channel(NioServerSocketChannel.class)//使用OioEventLoopGroup以允许阻塞模式
					.localAddress(new InetSocketAddress(port))
					.childHandler(new ChannelInitializer<SocketChannel>() {//制定ChannelInitializer对于每个已接收的连接都调用它
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {//团变化图个ChannelInboundHandlerAdapter拦截和处理时间
								@Override
								public void channelActive(ChannelHandlerContext ctx) throws Exception {
									//将消息写到客户端，并添加ChannelFutureListener以便消息已被写完就关闭连接
									ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
								}
							});
						}
					});
			//绑定服务器以接受连接
			ChannelFuture future = b.bind().sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
}
