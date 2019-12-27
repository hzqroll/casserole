package com.roll.casserole.jvm.classloader;

/**
 * @author roll
 * created on 2019-11-26 08:31
 * 线程上线问类加载器视同JDK1。2 开始引入的。类Thread中的getContextCLassLoader()与setContextClassLoaser(ClassLoader cl)
 * 分别用来获取和设置上下文类加载器。
 * 如果没有通过与setContextClassLoaser进行设置的话，线程将集成其父线程的类加载器。
 * Java应用程序运行时的初始现层的上下文类加载器是系统类加载器，在线程中运行的代码可以通过该类加载器来加载类与资源。
 *
 * 线程上下文类加载器的重要性：
 * SPI（Service Provider Interface）
 * 父ClassLoader可以使用当前线程Thread.getContextClassLoader()所指定的classLoader加载的类。
 * 这就改变了父classLoader不能使用子ClassLoader或是其他没有直接父子关系的ClassLoader加载的类的情况
 * 即改变了双庆委托模型。
 *
 * 线程上下文类加载器就是当前线程的Current ClassLoader
 * 在双亲委托模型下，类加载由下至上的，记下层的类加载器会委托上层进行加载，但对于SPI来说，有些接口是Java核心库所提供的，
 * 而Java核心库由启动类加载器来加载的，而这些接口的实现却来自于不同的jar包（厂商提供），Java的启动类加载器是不会加载其他来源的jar包，
 * 这样传统的双亲委托模型就无法满足SPI的要求，而通过当前线程设置上下文加载器，就可以由设置的上下文了加载器来实现对于实现类的加载。
 *
 */
public class MyTest24 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());

        System.out.println(Thread.class.getClassLoader());
    }
}
