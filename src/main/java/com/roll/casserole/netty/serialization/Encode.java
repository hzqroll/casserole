package com.roll.casserole.netty.serialization;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author roll
 * created on 2019-09-16 17:14
 */
public class Encode extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) {
        System.out.println("decode, message: " + msg.toString());
        byte[] data = JSONObject.toJSONBytes(msg);
        out.writeInt(data.length);
        out.writeBytes(data);
        System.out.println("encode, length：" + data.length + ", data: " + msg);
    }
}
