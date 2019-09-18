package com.roll.casserole.netty.serialization.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * @author roll
 * created on 2019-09-16 17:02
 */
public class SeriNettyClient {
    public static void main(String[] args) {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventExecutors);
        bootstrap.channel(NioSocketChannel.class);

        bootstrap.handler(new InitChildClient());
        bootstrap.option(ChannelOption.TCP_NODELAY, true);

        try {
            ChannelFuture future = bootstrap.connect("localhost",9100).sync();
            Channel channel = future.channel();
            channel.writeAndFlush(new Date() + "hello");
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
