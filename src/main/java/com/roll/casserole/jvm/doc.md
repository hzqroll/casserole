## 类的加载
## 类的验证
类被加载后，就进入连接阶段，连接就是将已经读入到内存的类的二进制合并到虚拟机的运行时环境中去。
类的验证内容
* 类文件的结构检查
* 语义检查
* 字节码验证
* 二进制兼容性的验证
类的准备
在准备阶段，Java 虚拟机为类的静态变量分配内存，并设置默认的初始值
类的初始化阶段，Java 虚拟机执行类的初始化语句，为类的静态变量赋予初始值。在程序中，静态变量的额初始化有两种途径：
1. 在静态变量的申明出进行初始化
2. 在静态代码块中进行初始化
静态变量的申明语句，以及静态代码块都被看做是类的初始化语句，Java 虚拟机会按照初始化的语句在类文件中的先后顺序来一次执行他们

类的初始化步骤：
* 假如这个类还没有被加载和连接，那就先进行加载和连接
* 假如类存在直接父类，并且这个父类还没有被初始化，那就先初始化直接父类
* 假如类中存在初始化语句，那就一次执行这初始化语句

## 类的初始化实际
当 Java 虚拟机初始化一个类时，要求它的所有父类都已经被初始化，但是这个规则并不适用于接口
* 在初始化一个类时，并不会先初始化他所实现的接口
* 在初始化一个接口时，并不会先初始化它的父接口
因此，一个父类接口并不会因为他的子接口或者实现类的初始化而初始化。只有当程序首次使用特定接口的静态变量时，才会导致该接口的初始化。
只有当程序访问的静态变量或静态方法确实在当前类或当前接口中定义时，才可以认为是对类或接口的主动使用。
调用 ClassLoader 类的 loadClass 方法加载一个类，并不是对类的主动使用，不会导致类的初始化。

类加载器用来把类加载到 Java 虚拟机中。类的加载过程采用父亲委托机制，这种机制能跟好的保证 Java 平台的安全
Java 虚拟机自带了以下几种加载器：
根类加载器：该加载器没有父加载器，他负责加载虚拟机核心类库
扩展类加载器;它的父加载器为根类加载器
系统类加载器，也成为应用类加载器，它的父类加载器为扩展类加载器。他也是用户自定义类加载器默认的父加载器，系统类加载器是纯 Java 类
用户还可以定制自己的类加载器，Java 提供了抽象类 ClassLoader，所有用户自定义的类加载器都应该继承 ClassLoader类。
获得 ClassLoader 的途径
* 获取当前类的加载器：clazz.getClassLoader()
* 获得当前线程上下文的 CLassLoader
Thread.currentThread().getContextClassLoader
* 获得系统的类加载器ClassLoader.getSystemClassLoader
* 获得调用者的类加载器 DriverManager.getCallerClassLoader

## 类加载器的双亲委托机制
父亲委托机制的优点是能够提高软件系统的安全性，因为在此机制下，用户自定义的类加载器不可能下载应该由父加载器加载的可靠类， 从而防止不可开甚至恶意代码地阿提由父类加载器加载的可靠代码。

## 命名空间
* 每个类加载器都有自己的命名空间，**命名空间由该类加载器以及所有父加载器所加载的类组成**
* 在同一个命名空间中，不会出现类的完整名字（包括类的包名）相同的两个类
* 在不同的命名空间中，有可能出现类的完整名字相同的两个类

## 创建用户自定义的类加载器
要创建用户字的类加载器，只需要扩展java.lang.ClassLoader 类，然后覆盖他的 findClass（Stringname）方法即可，该方法根据参数指定的类的名字，返回对应的 class 对象的引用

## 不同类加载器的命名空间关系
同一个命名空间的类是相互可见的
字加载器的命名空间包含的所有父加载器的命名空间，因此由子类加载器加载的类能看见父加载器加载的类
父加载器加载的类不能看见子类加载器加载的类
如果两个加载器之间没有直接或者间接的父子关系，那么他们各自加载的类湘湖不可见

## 类的卸载
当某个类被加载、连接、初始化后，他的生命周期就开始了，当代表这个类的 CLass 对象不再被引用，即不可触及时，Class 对象就会结束生命周期，这个类在方法区的数据也会被卸载，从而结束这个类的生命周期。
由 Java 虚拟机自带的类加载器所加载的类，在虚拟机的生命周期中，始终不会被卸载，Java 虚拟机自带的类加载器包括根类加载器、扩展类加载器和系统类加载器。





































 