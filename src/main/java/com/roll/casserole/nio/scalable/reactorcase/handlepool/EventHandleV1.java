package com.roll.casserole.nio.scalable.reactorcase.handlepool;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author roll
 * created on 2019-10-29 20:39
 */
public class EventHandleV1 {
    private static final ExecutorService handlerService = Executors.newFixedThreadPool(100);


    class EventHandlerTask implements Runnable {

        private Selector selector;

        private SelectionKey selectionKey;

        public EventHandlerTask(Selector selector, SelectionKey selectionKey) {
            this.selector = selector;
            this.selectionKey = selectionKey;
        }

        @Override
        public void run() {

        }
    }
}
