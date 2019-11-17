package com.roll.casserole.jvm.classloader;

/**
 * @author roll
 * created on 2019-11-17 14:45
 */
public class MyTest17_1 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        MyTest16 loader = new MyTest16("loader1");
        loader.setPath("/Users/haozongqiang/Downloads/temp/");
        Class<?> clazz = loader.loadClass("com.roll.casserole.jvm.classloader.MySample");
        System.out.println("class: " + clazz.hashCode());

        Object object = clazz.newInstance();
        System.out.println(object);
        //invoke find class
        //class: 824318946
        //MySample is loaded by: MyTest16{classLoaderName='loader1'}
        //invoke find class
        //MyCat is loaded by: MyTest16{classLoaderName='loader1'}
        //com.roll.casserole.jvm.classloader.MySample@377dca04

        //如果把文件中的mycat.class删除
        //class: 777874839
        //MySample is loaded by: sun.misc.Launcher$AppClassLoader@18b4aac2
        //Exception in thread "main" java.lang.NoClassDefFoundError: com/roll/casserole/jvm/classloader/MyCat
        //	at com.roll.casserole.jvm.classloader.MySample.<init>(MySample.java:11)
        //	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
        //	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
        //	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
        //	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
        //	at java.lang.Class.newInstance(Class.java:442)
        //	at com.roll.casserole.jvm.classloader.MyTest17_1.main(MyTest17_1.java:14)
        //Caused by: java.lang.ClassNotFoundException: com.roll.casserole.jvm.classloader.MyCat
        //	at java.net.URLClassLoader.findClass(URLClassLoader.java:382)
        //	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
        //	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
        //	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
        //	... 7 more

        // 如果删除mysample.class
        //invoke find class
        //class: 824318946
        //MySample is loaded by: com.roll.casserole.jvm.classloader.MyTest16@2e5d6d97
        //MyCat is loaded by: sun.misc.Launcher$AppClassLoader@18b4aac2
        //com.roll.casserole.jvm.classloader.MySample@377dca04

        // 添加打印行后，删除MySample
        //invoke find class
        //class: 824318946
        //MySample is loaded by: com.roll.casserole.jvm.classloader.MyTest16@2e5d6d97
        //MyCat is loaded by: sun.misc.Launcher$AppClassLoader@18b4aac2
        //Exception in thread "main" java.lang.NoClassDefFoundError: com/roll/casserole/jvm/classloader/MySample
        //	at com.roll.casserole.jvm.classloader.MyCat.<init>(MyCat.java:11)
        //	at com.roll.casserole.jvm.classloader.MySample.<init>(MySample.java:11)
        //	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
        //	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
        //	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
        //	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
        //	at java.lang.Class.newInstance(Class.java:442)
        //	at com.roll.casserole.jvm.classloader.MyTest17_1.main(MyTest17_1.java:14)
        //Caused by: java.lang.ClassNotFoundException: com.roll.casserole.jvm.classloader.MySample
        //	at java.net.URLClassLoader.findClass(URLClassLoader.java:382)
        //	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
        //	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
        //	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
        //	... 8 more
    }
}
