package com.roll.casserole.netty.serialization;

import com.alibaba.fastjson.JSONObject;


/**
 * @author roll
 * created on 2019-09-21 14:28
 */
public class JavaSeriTest3 {
    public static void main(String[] args) {

    }

    /**
     * 序列化，转换为二进制数据,保存到文件中
     *
     * @param person
     */
    private void seriablizable(Person person) {
        String personString = JSONObject.toJSONString(person);
    }

    /**
     * 从文件中读取数据，反序列化为制定对象
     */
    private void deriablizable() {
    }
}
