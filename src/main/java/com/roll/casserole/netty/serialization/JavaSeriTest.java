package com.roll.casserole.netty.serialization;

import java.io.*;
import java.sql.Date;

/**
 * @author roll
 * created on 2019-09-18 19:26
 */
public class JavaSeriTest {

    private static final String path = "/Users/haozongqiang/Downloads/temp/seri.txt";

    public static void main(String[] args) {
        Person person = new Person("zhang三", 11, true, new Date(System.currentTimeMillis()));

        JavaSeriTest javaSeriTest = new JavaSeriTest();
        javaSeriTest.seriablizable(person);
        javaSeriTest.deriablizable(null);
    }

    /**
     * 序列化，转换为二进制数据,保存到文件中
     *
     * @param person
     */
    private void seriablizable(Person person) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            objectOutputStream.writeObject(person);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 从文件中读取数据，反序列化为制定对象
     *
     * @param data
     */
    private void deriablizable(byte[] data) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
            Person person = (Person) objectInputStream.readObject();
            System.out.println(person.toString());
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
