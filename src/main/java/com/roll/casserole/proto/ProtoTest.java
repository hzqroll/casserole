package com.roll.casserole.proto;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author zongqiang.hao
 * created on 2019-06-04 21:14.
 */
public class ProtoTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("roll").setAge(20).setAddress("beijing").build();
        byte[] studenttoByteArray = student.toByteArray();

        DataInfo.Student student1 = DataInfo.Student.parseFrom(studenttoByteArray);

        System.out.println(student1);
    }
}
