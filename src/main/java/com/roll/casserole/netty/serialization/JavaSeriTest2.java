package com.roll.casserole.netty.serialization;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.*;
import java.sql.Date;

/**
 * @author roll
 * created on 2019-09-19 09:18
 */
public class JavaSeriTest2 {

    private static final String path = "/Users/haozongqiang/Downloads/temp/jsonseri.txt";

    public static void main(String[] args) {
        Person person = new Person("zhang三", 11, true, new Date(System.currentTimeMillis()));

        byte[] data = JSONObject.toJSONBytes(person, SerializerFeature.WRITE_MAP_NULL_FEATURES);
        Person person1 = JSONObject.parseObject(data, Person.class);
        System.out.println(person1.toString());
        //JavaSeriTest2 javaSeriTest = new JavaSeriTest2();
        //javaSeriTest.seriablizable(person);
    }

    /**
     * 序列化，转换为二进制数据,保存到文件中
     *
     * @param person
     */
    private void seriablizable(Person person) {
        String personString = JSONObject.toJSONString(person);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(personString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读取数据，反序列化为制定对象
     */
    private void deriablizable() {
    }
}
