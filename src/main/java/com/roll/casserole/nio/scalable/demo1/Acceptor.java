package com.roll.casserole.nio.scalable.demo1;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * selectionKey 分发器
 * <p>@author roll
 * <p>created on 2020/7/29 9:34 下午
 */
public interface Acceptor {
    /**
     * 分发事件，根据类型来分发
     *
     * @param selectionKey   当前的selectionkey
     * @param selector       selector
     * @param channelHandler 事件处理器
     */
    void dispatch(SelectionKey selectionKey, Selector selector, ChannelHandler channelHandler);
}
