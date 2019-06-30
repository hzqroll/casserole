package com.roll.casserole.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zongqiang.hao
 * created on 2018/10/25 上午11:54.
 */
public class CopyUtils {
    private static List<String> oriList = new ArrayList<>();

    private static List<String> newList = new ArrayList<>();

    static {
        oriList.add("a");
        oriList.add("b");

        newList.add("c");
    }

    public static List<String> shallowCopy(List<String> list1) {
        //System.out.println("1 ori list: " + list1.toString());
        List<String> shallowList = list1;
        shallowList.add(0, "c");
        //System.out.println("2 ori list: " + list1.toString());
        return shallowList;
    }

    public static void deepCopy() throws CloneNotSupportedException {
        Car oriCar = new Car();
        oriCar.setName("car1");
        oriCar.setWheels(oriList);
        //System.out.println(oriCar.toString());

        Car newCar = oriCar.clone();
        newCar.setWheels(shallowCopy(newCar.getWheels()));
        //System.out.println(newCar.toString());

        //System.out.println(oriCar.toString());

        Car newCar1 = null;
        try {
            ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayInputStream);
            objectOutputStream.writeObject(oriCar);

            //分配内存，写入原始对象，生成新对象
            ByteArrayInputStream ios = new  ByteArrayInputStream(byteArrayInputStream.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);
            //返回生成的新对象
            newCar1 = (Car) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        newCar1.setWheels(shallowCopy(newList));
        newCar1.setName("new car 1");
        System.out.println(newCar1.toString());

        System.out.println(oriCar.toString());
    }

    public static void main(String args[]) throws CloneNotSupportedException {
        //shallowCopy();
        deepCopy();
    }
}
