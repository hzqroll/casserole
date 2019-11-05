package com.roll.casserole.jvm.classloader;

import java.io.*;

/**
 * @author roll
 * created on 2019-11-04 08:48
 */
public class MyTest16 extends ClassLoader {
    private String classLoaderName;

    private final String fileExtention = ".class";


    public MyTest16(String classLoaderName) {
        super();// 将系统类加载器，当作改类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public MyTest16(ClassLoader parent, String classLoaderName) {
        super(parent);// 显示指定该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    @Override
    public String toString() {
        return "MyTest16{" +
                "classLoaderName='" + classLoaderName + '\'' +
                '}';
    }

    public Class<?> findClass(String name) {
        byte[] data = this.loaderClassData(name);
        return this.defineClass(classLoaderName, data, 0, data.length);

    }

    private byte[] loaderClassData(String name) {
        InputStream in = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        try {
            this.classLoaderName = this.classLoaderName.replace(".", "/");
            in = new FileInputStream((new File(name + fileExtention)));
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

    public static void test(ClassLoader classLoader) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = classLoader.loadClass("com.roll.casserole.jvm.classloader.MyTest1");
        Object object = clazz.newInstance();
        System.out.println(object.toString());
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        MyTest16 loader = new MyTest16("loader1");
        test(loader);
    }
}
