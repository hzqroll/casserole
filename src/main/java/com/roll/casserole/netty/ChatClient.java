package com.roll.casserole.netty;

/**
 * @author roll
 * created on 2019-09-24 10:58
 */


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author wangsheng
 * @date 2019/9/2
 */
public class ChatClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
                    pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                    pipeline.addLast(new SimpleChannelInboundHandler<String>(

                    ) {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                            System.out.println(msg);
                        }
                    });
                }
            });
            Channel channel = bootstrap.connect("10.57.241.225", 8889).sync().channel();
            //读取控制台输入的内容
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                channel.writeAndFlush(bufferReader.readLine() + "\r\n");
            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
