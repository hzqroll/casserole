package com.roll.casserole.nio.scalable.threadpool;

import java.nio.channels.SelectionKey;

/**
 * @author roll
 * created on 2019-07-21 21:43
 */
public class Dispatcher {

    public Dispatcher(SelectionKey selectionKey) {
        // handler 塞进去的是一个任务
        Runnable runnable = (Runnable) selectionKey.attachment();
        if (runnable != null) {
            runnable.run();
        }
    }
}
