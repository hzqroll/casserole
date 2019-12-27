package com.roll.casserole.jvm.classloader;

/**
 * @author roll
 * created on 2019-12-26 08:37
 */
public class MyTest25 implements Runnable {
    private Thread thread;

    public MyTest25() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        ClassLoader classLoader = this.thread.getContextClassLoader();
        //this.thread.setContextClassLoader(classLoader);

        System.out.println("class " + classLoader.getClass());
        System.out.println("parent " + classLoader.getParent().getClass());
    }

    public static void main(String[] args) {
        new MyTest25();
    }
}
