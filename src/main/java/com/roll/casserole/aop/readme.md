# 1. 什么是AOP
## 1.1 静态AOP
    静态AOP,以最初的Aspectj为代表, 特点是, 相应的横切关注点以Aspectj编译并织入到系统的静态类中.比如, Aspectj会使用ajc编译器将各个Aspect以java字节码的形式编译到系统的各个功能模块中, 已达到融合Aspect和class的目的.
 > 优点是Aspect直接以java字节码的形式编译到java类中,不会对整个系统的运行造成任何的性能损失,缺点是不够灵活, 如果横切关注点需要改变织入到系统的位置, 就需要重新修改Aspect定义文件, 然后使用编译器重新编译Aspect到文件中

## 1.2 动态AOP
        AOP的织入过程是通过在系统裕兴开始之后进行，而不是预先编译到系统类中，而且织入的信息大豆采用我i不XML文件格式保存，可以在调整织入点以及织入逻辑单元的同时，不必变更其他系统的模块，甚至在系统
        
## 1.3 java平台上的AOP实现机制
    在java平台可以使用多种方式实现AOP.下面提到的几种方式是最近常使用的.
### 1.3.1 动态代理
    Dynamic Proxy机制,可以在运行期间,为相应的接口动态生产相应的代理对象.所以, 我们可以将很横切关注点逻辑封装到动态代理的InvocationHandler中,然后在运行期间,根据横切关注点需要织入的模块位置,将横切逻辑织入到相应的代理中.
    这种方式实现的唯一缺点或者说优点就是,所有需要织入横切关注点的逻辑模块都需要实现相应的接口,因为动态代理机制只针对接口有效.Spring AOP默认情况下采用这种实现机能, Naning也是, 只支持动态代理机制.
    
 ### 1.3.2 动态字节码增强
    通过动态字节码增强技术，为这些系统模块类生成相应的子类，而将横切逻辑加到这些子类中，让应用程序在执行期间使用的是这些动态是个生成的子类， 从而达到将横切逻辑织入系统的目的。同故宫动态字节码增强技术，即使模块类没有事项相应的接口，我们依然可以对其进行扩展，而不用像动态代理那样受限于接口，不过，这种实现机制依然存在不粗，如果需要扩展的类以及类中的实力方法等申明为final，无法进行扩展。
    
 ### 1.3.3 自定义类加载器![670f1cb65dc569b20c547262c7a7cd6a.png](evernotecid://35A6A25A-7C8E-4C17-B0D1-A61CAFA08110/appyinxiangcom/8571843/ENResource/p710)
 
    所有的java程序的class都要同通过相应的类加载器（classLoader）加载到java虚拟机才可以运行，默认的类加载器会读取class字节码文件，然后按照class籽伽玛规范，解析并加载这些class文件到虚拟机运行。
    我们可以通过自定义类加载器的方式完成横切逻辑到系统的织入，自定义类加载器文件对顶的织入规则和必要信息，在加载class文件期间就可以将横切逻辑添加到系统模块类的先有个逻辑中，然后把改动后的class交给虚拟机运行
    通过类加载器，我们基本可以对大部分类以及相应的实力进行织入 ，功能与之前的几种方式相比当然强大过很多，不过这种方式的最大问题就是类加载器本身的使用。某些应用服务器会控制整个类的加载体系，所有这样的场景下使用坑内或造成一定的问题，。
    JBoss AOP和以及并入AspectJ项目的AspectWerkz狂欢价都是使用自定义类加载器的方式实现。
    
# 2. AOP Concepts一些概念
![670f1cb65dc569b20c547262c7a7cd6a.png](evernotecid://35A6A25A-7C8E-4C17-B0D1-A61CAFA08110/appyinxiangcom/8571843/ENResource/p710)

## 2.1 JoinPoint
    在系统运行之前，AOP的功能模块都需要织入到OOP的功能模块中，所以要进行这种织入过程，我们需要知道在系统的哪些执行点上进行织入操作，这些将要在其之上进行织入操作的系统制定点就成为JoinPoint。

> 以下是较为常见的JoinPoint类型

* 方法调用（method Call）。当某个方法被调用的时候所处的程序执行点
* 方法调用执行（Method Call execution）称之为方法执行或许更简洁，该Joinpoint类型代表的是某个方法内部执行开始点，影响与方法调用类型的Joinpoint进行区分 
* 构造方法调用（constructor Call）程序执行过程中对某个对象调用其构造方法进行初始化的时间点
* 构造方法执行（Constructor Call Execution）
* 字段设置(Field Set). 对象的某个属性通过setter方法被设置或直接被设置的时点
* 字段获取
* 异常处理执行(Exceotion Handler Execution)给来写的JOinpoint队形的程序执行过程中,在某些类型异常抛出后,对应的异常处理逻辑执行的时点.
* 类初始化 (class Initialization) 指的是类中某些静态类型或者静态块的初始化时点

## 2.2 Pointcut
    pointcut概念代表的是Joinpoint的表述方式, 将横切逻辑织入房钱系统的过程中, 需要参照Pointcut规定的joinpoint信息,才可以知道应该往系统的那些Joinpoint上织入横切逻辑.
    我们使用自热语言声明一个Pointcut, 改Pointcut指定了系统中符合条件的一组Joinpoint. 不过, 实际系统中我们不可能使用自然语言的形式Pointcut
    
### 2.2.1 Pointcut的表述方式
    当前的AOP产品所使用的Pointcut表达形式通常可以简答划分为以下几种
* **直接指定Joinpoint所在方法名称** 这种形式的Pointcut表述方式鼻尖简单,而且功能单一, 通常只限于支持方法级别的Joinpoint的AOP框架, 或者只是方法调用类型的Joinpoint, 或者只是方法执行类型的Joinpoint.
* **正则表达式**
* **使用特定的Pointcut表述语言** 

## 2.3 Advice
    Advice是单元一横切关注点逻辑的载体, 它代表将会织入到Joinpoint的横切逻辑, 如果将Adpect比作OOP中的Class , nameAdvice就相当于class 中的method.
    按照Advice在Joinpoint位置执行时机的差异或者完成功能的不同, Advice可以分成多种具体形式

1. **Before Advice** 是在Joinpoint指定位置之前执行的Advice类型. 通常, 他不会中断程序执行流程, 但如果必要, 可以通过在before Advice中抛出异常的方式来中断当前程序流程. 如果当前before Advice奖杯织入到方法执行类型的JoinPoint, name这个before Advice将会优先于方法执行而执行.
2. **After Advice**. 分为三种情况: 
* after returning advice. 只有当前Joinpoint出执行流程正常完成后, after returning才会执行, 比如方法执行正常返回而没有抛出异常
* after throwing advice 又称为Throw是 advice 只有在当前Joinpoint执行过程中抛出异常的情况下, 才会执行,
* after advice. 或许叫finally afvice 更为确切, 该类型的adbvice不管JoinPoint出执行流程是正常终了还是抛出异常都会执行
     ![a770ae9d09ba23b44494a8b13ebfec9f.png](evernotecid://35A6A25A-7C8E-4C17-B0D1-A61CAFA08110/appyinxiangcom/8571843/ENResource/p708)
     
3. **Around Advice**, AOp Alliance属下的AOP实现大豆采用拦截器(Interceptor)的叫法, 但完成的功能是一样的. Around Advice对附加其上的JoinPoint进行包裹, 可以在JoinPoint之前和之后都指定相应的逻辑, 甚至于中断或者忽略JoinPoint出原来程序流程的执行.
4. **Introduction** . 在Aspect中成为Inter-Type Declaration, 不是根据横切逻辑在JoinPoint出的执行来区分的, 二十genuine他可以完成的功能而区别于其他Advice类型. Introduuction可以为原有的对象添加新的特性或者行为/

## 2.4 Aspect
    切面是通知和切点的结合.通知和切点共同定义了切面的全部内容
    
## 3.5 Introduction
    引入, 允许我们向现有的类添加新方法或属性.
    
## 3.6 织入weaver
    完成横切关注点逻辑到系统的最终织入
    
    
# 4 Spring AOP概述机器实现机制
    
## 4.1 各元素在AOP体现

### 4.1.1 JoinPoint
    Spring中仅支持方法级别的point
    
### 4.1.2 Pointcut
    
    看Pointcut方法的介绍
    
> A pointcut is composed of a ClassFilter and a MethodMatcher. Both these basic terms and a Pointcut itself can be combined to build up combinations:切点有ClassFilter和MethodMatcher组合.每一个基本属于和切点本省都能够组合去构建组合
> CLassFilter和MethodMatcher分别用于匹配奖杯执行织入操作的对象已经相应的方法

* ClassFilter方法:
    boolean matches(Class<?> clazz);
	ClassFilter TRUE = TrueClassFilter.INSTANCE;
当织入的目标对象的class类型与Pointcut所规定的类型相符时,matchers方法返回true.
如果返回的是TRUE的时候,会对所有的目标类已经湿了进行匹配

* MethodMatcher
	boolean matches(Method method, Class<?> targetClass);
	boolean isRuntime();
	boolean matches(Method method, Class<?> targetClass, Object[] args);
在对具体对象进行拦截的时候,
如果isTUNtime返回false,标识不会考虑具体的JoinPoint的方法参数.会对匹配的结果进行缓存
           返回true, 表明该MethodMatcher将会每次都对方法调用的参数进行匹配检查. 不进行缓存.


>常见的Pointcut

1. NameMatchMethodPointcut, 可以根据自身指定的一组方法名称与JoinPoint处的方法的方法名称进行匹配
2. RegexpMethodPointcut, 
3. AnnotationMatchingPointcut, 根据对象中是否存在指定类型的注解来匹配Joincut,要使用该类型的Pointcut,首先要什么申明相应的注解
4. ComposablePointcut,提供逻辑运算
5. ControllerFlowPointcut, 匹配程序的调用流程, 不是对某个方法执行所在的JoinPoint处额单一特征进行匹配. 可以指定切某个方法(在被指定的某个类调用的时候,才去切).需要在运行期间检查程序的调用栈,性能比较差.


### 4.1.3 Advice
    
>pre-class类型的Advice, 是指该类型的Advice的实例可以在目标对象类所在的所有实例之间共享. 这种类型的Advice通常只是提供方法拦截的功能,不会为目标对象类保存任何状态或者添加新的特性.

**1. Before Advice**
    Before Advice所实现的横切逻辑将在相应的JoinPoint之前执行, 在BeforeAdvice执行完成之后, 程序执行流程将从JoinPoint处继续执行, 所以Before Advice不会打断程序的执行流程. 但是如果必要, 也可以通过抛出相应异常的形式中断程序流程.
**2. ThrowAdvice**
    可以实现拦截不同的抛出异常.ThrowAdvice通常用于对系统中的特定的异常情况进行监控, 以统一的方式对所发生的异常进行处理, 我们可以在一中称之为Fault Barrier的模式中使用它.
**3. AfterReturningAdvice**
    通过AfterReturningAdvice, 我们可以访问当前JoinPoint的方法返回值,防范,方法参数以及所在的目标对象.因为只有方法正常返回的时候, AfterReturningAdvice才会执行, 所以用来处理资源清理类的工作并不合适.不可以更改返回值.
**4. AroundAdvice**
    MethodInterceptor作为AroundAdvice,
    
>per-instance类型的Advice. per-instance类型的Advice不会再目标类所有对象实例之间共享,二十回味不同的实例对象保存他们各自的状态以及相关逻辑.
**Introduction**
    是唯一的一种per-instance的Advice. Introduction可以在不改变目标类定义的情况下, 为目标添加新的属性以及行为. 在Spring中,为目标添加新的属性和行为必须声明相应的接口以及相应的事项.这样,再通过特定的拦截器将新的接口定义以及实现类中的逻辑添加到目标对象之上,之后目标对象(确切的说是目标对象的代理对象)就拥有了新的主功能太和行为,这个特定的拦截器就是IntruductionInterceptor.
    其中implementsInterface()方法返回ture, 标识目前的IntroductionInterceptor实现了给定的接口(也就是要额外增加行为的接口),此时要使用invoke()调用该接口上的防范,让目标执行额外的行为.需要注意的是不可能使用MethodInvocation的proceed()方法, 因为要执行的是类原来没有的行为,priceed()方法没有意义.
    性能问题, 与Aspect照顾会题本公告一编译器将introduction织入目标对象不容,spring AOP是动态代理机制, 性能上, introduction型的Advice要逊色不少.
```java

public interface IntroductionInterceptor extends MethodInterceptor, DynamicIntroductionAdvice {
}
```

### 4.1.4 Spring AOP中的Aspect
    Advisor代表Spring中的Aspect,但是与正常的Aspect不同, Advisor通常只持有一个Pointcut和一个Advice.理论上,Aspect定义中可以有多个Pointcut和多个Advice,所以, 我们可以认为Advisor是一种特殊的Aspect.
[![Advice实现](https://i.loli.net/2019/03/10/5c850801455b5.png)](https://i.loli.net/2019/03/10/5c850801455b5.png)
    
**1. PointCutAdvisor家族**
    实际上, PointcutAdvisor才是真正定义一个Pointcut和一个Advice的Advisor, 大部分的Advisor实现全都是Pointcut的部下.
    [![PintcutAdvisor家族](https://i.loli.net/2019/03/11/5c86441041c84.png)](https://i.loli.net/2019/03/11/5c86441041c84.png)
    
**2. Order作用**
    系统中大多存在多个Advisor 如果不指定了顺序, nameSpring会按照他们的申明顺序来应用他们,最先申明的顺序号最小优先级最大
    
### 4.1.5 Spring AOP的织入
    要进行织入,AspectJ采用ajc编译器作为它的织入器,JBOSSAOP使用自定义的ClassLoader最为它的织入器, 而在Spring AOP中,使用proxyFactory作为织入器.

**1. 如何与ProxyFactory打交道**
    使用ProxyFactory来进行横切逻辑的织入很简单, SPringAOP是基于代理模式的AOP实现, 织入过程完成后, 会返回植入了横切逻辑的目标对象的代理对象. 为ProxyFactory提供必要的"生产原材料"之后,ProxyFactory就会返回哪个织入完成的代理对象.
    使用ProxyFactory只需要指定如下两个最基本的东西.
* [ ] 第一个是要对其进行织入的目标对象.
* [ ] 第二个将要应用到目标对象的Aspect
1. 基于接口的代理
    
2. 基于类的代理    
    如果目标类没有实现任何接口, name默认情况下,,ProxyFactory会对目标进行基于类的代理,即使用CGLIB
    满足下面情况的任何一种情况,ProxyFactory将对目标进行基于类的代理.
* 如果目标对象没有实现任何接口, 不管ProxyTargetClass的值是什么,ProxyFactory都会采用基于类的代理
* 如果ProxyFactory的ProxyTargetClass属性值被设置为true, ProxyFactory会采用基于类的代理
* 如果ProxyFactory的optimize属性值被指为为true, 采用基于类的代理.

3. Introduction织入
    Introduction可以为依据存在的对象类型添加新的欣慰,只能应用于对象级别的拦截, 而不是Advie的方法级别拦截. 所哟进行Introduction的织入过程, 不需要指定Pointcut,而只需要指定目标接口类型.
    Spring的Introduction支持只能通过接口定义为当前对象添加新的行为,所以,我们需要在织入的实际, 指定新织入接口类型.
    
**2. 看清ProxyFactory的本质**
```java
public interface AopProxy{
    Object getProxy();
    Object getProxy(ClassLoader classloader);
}
```
    Spring AOP框架内使用AOPProxy对使用不同的代理实现进行了适度的抽象,针对不同的代理实现机制提供相应的AopProxy子类实现.
    [![AopProxy](https://i.loli.net/2019/03/11/5c865bb7199ac.png)](https://i.loli.net/2019/03/11/5c865bb7199ac.png)