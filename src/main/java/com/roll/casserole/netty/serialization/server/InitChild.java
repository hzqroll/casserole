package com.roll.casserole.netty.serialization.server;

import com.roll.casserole.netty.serialization.Decode;
import com.roll.casserole.netty.serialization.Encode;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author roll
 * created on 2019-09-16 17:09
 */
public class InitChild extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
        ch.pipeline().addLast(new Encode());
        ch.pipeline().addLast(new Decode());
        ch.pipeline().addLast(new SimpleChannelInboundHandler<Object>() {
            @Override
            protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
                System.out.println(msg);
            }
        });

    }
}
