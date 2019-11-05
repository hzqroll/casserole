package com.roll.casserole.nio.scalable.reactorcase.reactorv3.handle;

import java.nio.channels.SelectionKey;

/**
 * 事件处理器接口
 *
 * @author roll
 * created on 2019-11-02 18:11
 */
public interface EventHandler {

    /**
     * 处理数据
     *
     * @param selectionKey selectionKey
     */
    void channelRead(SelectionKey selectionKey);

    //void channelActive(SelectionKey selectionKey);
}
