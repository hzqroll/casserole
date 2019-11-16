package com.roll.casserole.jvm.classloader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author roll
 * created on 2019-11-04 08:48
 */
public class MyTest16 extends ClassLoader {
    private String classLoaderName;

    private String path;

    public void setPath(String path) {
        this.path = path;
    }

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

    @Override
    public Class<?> findClass(String name) {
        System.out.println("invoke find class");

        byte[] data = this.loaderClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }

    private byte[] loaderClassData(String name) {
        InputStream in = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        try {
            String filePath = path + name.replace(".", "/") + ".class";
            in = new FileInputStream((new File(filePath)));
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
       /* MyTest16 loader = new MyTest16("loader1");
        loader.setPath("/Users/haozongqiang/Downloads/temp/");
        test(loader);*/

        String e = "{\"result\":\"1.0\",\"hits\":[{\"evidence_time\":\"2019-02\",\"case_no\":\"(2019)苏0582执恢110号\",\"risk_type\":\"limit_high_consumption\",\"subject_type\":\"individual\",\"case_create_date\":\"2019-02-01\",\"court_name\":\"张家港市人民法院\"}],\"type\":\"creditList_index_detail\"}";
        JSONObject je = JSON.parseObject(e);
        JSONArray jsonArray = new JSONArray();
        JSONObject a = new JSONObject();
        a.put("a", "b");
        JSONObject b = new JSONObject();
        b.put("c", "d");
        jsonArray.add(a);
        jsonArray.add(b);
        jsonArray.add(je);
        System.out.println(jsonArray);
        JSONArray hits = jsonArray;
        List<String> list = new ArrayList<>();
        for (Object hit : hits) {
            if (hit != null) {
                JSONObject hitObject = (JSONObject) hit;
                System.out.println(hit);
                if (!StringUtils.equals(hitObject.getString("risk_type"), "credit_crack")) {
                    hit = new JSONObject();
                }
            }
        }

        List<JSONObject> integerList = new ArrayList<>();

        for (int i = 0; i < hits.size(); i++) {
            JSONObject hit = (JSONObject) hits.get(i);

            if (hit != null) {
                System.out.println(hit);
                if (!StringUtils.equals(hit.getString("risk_type"), "limit_high_consumption")) {
                    //hits.set(i, new JSONObject());
                    integerList.add(hit);
                }
            }
        }
        hits.removeAll(integerList);
        System.out.println("----------");
        System.out.println(jsonArray);
    }
}
