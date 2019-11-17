package com.roll.casserole.jvm.classloader;

import java.io.*;

/**
 * @author roll
 * created on 2019-11-04 08:48
 */
public class MyTest16 extends ClassLoader {
    private String classLoaderName;

    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public MyTest16(String classLoaderName) {
        super();// 将系统类加载器，当作改类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public MyTest16(ClassLoader parent, String classLoaderName) {
        super(parent);// 显示指定该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    @Override
    public Class<?> findClass(String name) {
        System.out.println("invoke find class");

        byte[] data = this.loaderClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }

    private byte[] loaderClassData(String name) {
        InputStream in = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        try {
            String filePath = path + name.replace(".", "/") + ".class";
            in = new FileInputStream((new File(filePath)));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = in.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            try {
                in.close();
                baos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        MyTest16 loader = new MyTest16("loader1");
        loader.setPath("/Users/haozongqiang/Downloads/temp/");
        Class<?> clazz = loader.loadClass("com.roll.casserole.jvm.classloader.MyTest1");
        Object object = clazz.newInstance();
        System.out.println(object.toString());

        loader = null;
        clazz = null;
        object = null;
        System.gc();

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loader = new MyTest16("loader1");
        loader.setPath("/Users/haozongqiang/Downloads/temp/");
        clazz = loader.loadClass("com.roll.casserole.jvm.classloader.MyTest1");
        object = clazz.newInstance();
        System.out.println(object.toString());
        // 加上-XX:+TraceClassUnloading, 输出 [Unloading class com.roll.casserole.jvm.classloader.MyTest1 0x00000007c0061028]
        // 系统类加载器加载的类不会被卸载，因为虚拟机会一直应用系统类加载器加载的类
    }
}
