package com.roll.casserole.netty.second;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author zongqiang.hao
 * created on 2019-07-06 12:06.
 */
public class MyInitializerChannel extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        // 粘包拆包问题，基于数据长度的解码器
        channelPipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        //
        channelPipeline.addLast(new LengthFieldPrepender(4));
        //
        channelPipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        //
        channelPipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));

        channelPipeline.addLast(new MyServerHandler());

    }
}
