package com.roll.casserole.netty.serialization;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author roll
 * created on 2019-09-16 17:17
 */
public class Decode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        System.out.println("decode, buffer length: " + in.writerIndex());
        
        int length = in.readInt();

        byte[] data = new byte[length];
        in.readBytes(data);
        out.add(JSONObject.parse(data));
        System.out.println("decode, length：" + data.length + ", data: " + out);
    }
}
