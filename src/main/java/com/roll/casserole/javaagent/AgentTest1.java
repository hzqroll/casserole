package com.roll.casserole.javaagent;

import javassist.*;

import java.lang.instrument.Instrumentation;

/**
 * javaagent示例
 *
 * @author roll
 * created on 2019-10-05 19:49
 */
public class AgentTest1 {

    public static void premain(String arg, Instrumentation instrumentation) {
        System.out.println("装载成功！参数： " + arg);
        instrumentation.addTransformer((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            if (className.replaceAll("/", ".").equals("com.roll.casserole.javaagent.TransformTest1")) {
                ClassPool classPool = new ClassPool();
                classPool.insertClassPath(new LoaderClassPath(loader));
                CtClass ctClass;
                try {
                    ctClass = classPool.get("com.roll.casserole.javaagent.TransformTest1");

                    CtMethod ctMethod = ctClass.getDeclaredMethod("print");
                    ctMethod.insertBefore("System.out.println(123);");
                    return ctClass.toBytecode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        });
    }
}
