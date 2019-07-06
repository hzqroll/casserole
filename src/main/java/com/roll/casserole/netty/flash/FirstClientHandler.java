package com.roll.casserole.netty.flash;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * 客户的先关逻辑代码读写是通过bootstrap的handler()方法指定的<p></p>
 * 现在我们在initChannel方法里面给客户端添加一个逻辑处理器,这个处理器的作用就是负责向服务端写数据
 *
 * @author zongqiang.hao
 * created on 2018-12-08 14:18.
 */

@Service
public class FirstClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ": 123客户端读取数据" + byteBuf.toString(Charset.forName("utf-8")));
    }

    /**
     * 覆盖方法channelActive 这个方法会在客户端连接成功后被调用<p></p>
     * 客户端连接建立成功之后,调用channelActive方法,在这个方法里面,我们编写向服务端写数据逻辑<p></p>
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "客户端写出数据");
        ByteBuf byteBuf = ctx.alloc().buffer();
        byte[] bytes = "hello, roll".getBytes(Charset.forName("utf-8"));
        byteBuf.writeBytes(bytes);
        ctx.channel().writeAndFlush(byteBuf);
    }
}
