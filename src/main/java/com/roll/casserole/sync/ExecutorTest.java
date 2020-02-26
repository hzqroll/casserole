package com.roll.casserole.sync;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

/**
 * @author roll
 * created on 2019-12-27 09:41
 */
public class ExecutorTest implements Executor {
    @Override
    public void execute(@NotNull Runnable command) {
        command.run();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("task start");
                Thread.sleep(3000);
                System.out.println("task end");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("main start");
        ExecutorTest executorTest = new ExecutorTest();
        Task task = new Task();
        executorTest.execute(task);
        System.out.println("main end");
    }
}
