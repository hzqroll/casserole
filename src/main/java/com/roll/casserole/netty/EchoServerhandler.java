package com.roll.casserole.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author haozq
 * Date: 2018/8/11 下午2:45
 */
public class EchoServerhandler extends ChannelInboundHandlerAdapter {
	/**
	 * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward
	 * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
	 * <p>
	 * Sub-classes may override this method to change behavior.
	 * 对于每个传入的消息都要调用
	 *
	 * @param ctx
	 * @param msg
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		System.out.println("Server reciver: " + in.toString(CharsetUtil.UTF_8));
		ctx.write(in);
	}

	/**
	 * Calls {@link ChannelHandlerContext#fireChannelReadComplete()} to forward
	 * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
	 * <p>
	 * Sub-classes may override this method to change behavior.
	 * 通知ChanddelInoudHandler最后一次对ChannelRead()的调用时当前批量读取中的最后一条消息
	 *
	 * @param ctx
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}

	/**
	 * Calls {@link ChannelHandlerContext#fireExceptionCaught(Throwable)} to forward
	 * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
	 * <p>
	 * Sub-classes may override this method to change behavior.
	 * 在读取操作期间，有异常抛出会调用
	 *
	 * @param ctx
	 * @param cause
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
