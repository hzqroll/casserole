package com.roll.casserole.jvm.invoke;

import java.lang.invoke.*;

/**
 * @author zongqiang.hao
 * created on 2018-12-17 19:44.
 */
public class Circuit {
    public static void startRace(Object object) {
        //MethodHandles.Lookup = Circuit
    }

    public static void main(String[] args) {
        startRace(new com.roll.demos.jvm.invoke.Horse());
    }

    public static CallSite bootstrap(MethodHandles.Lookup l, String name, MethodType callSiteType) {
        MethodHandle mh = null;
        try {
            mh = l.findVirtual(com.roll.demos.jvm.invoke.Horse.class, name, MethodType.methodType(void.class));
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ConstantCallSite(mh.asType(callSiteType));
    }
}
