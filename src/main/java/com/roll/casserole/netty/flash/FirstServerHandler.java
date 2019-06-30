package com.roll.casserole.netty.flash;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author zongqiang.hao
 * created on 2018-12-16 14:15.
 */
@Service
public class FirstServerHandler extends SimpleChannelInboundHandler {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ": 123服务端读取数据" + byteBuf.toString(Charset.forName("utf-8")));
    }

    /**
     * 这个方法在接收到客户端发来的数据之后被回调
     *
     * @param ctx
     * @param msg 这个的msg指的就是netty里面数据读写的载体
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ": 服务端读取数据" + byteBuf.toString(Charset.forName("utf-8")));
    }

}
