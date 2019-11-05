package com.roll.casserole.nio.scalable.reactorcase.reactorv3;

import com.roll.casserole.nio.scalable.reactorcase.reactorv3.handle.EventHandler;
import com.roll.casserole.nio.scalable.reactorcase.reactorv3.handle.EventHandlerImpl;

import java.nio.channels.SelectionKey;

/**
 * 数据事件处理器
 * 处理read事件和write事件
 *
 * @author roll
 * created on 2019-10-29 20:39
 */
public class EventHandleV3 {

    private EventHandler eventHandler = new EventHandlerImpl();

    public void handle(SelectionKey selectionKey) {
        eventHandler.channelRead(selectionKey);
    }
}
