package com.roll.casserole.buffer;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author roll
 * created on 2019-08-24 17:45
 */
public class BufferTest {
    public static void main(String[] args) {
        get1();
        get2();
        get3();

    }

    private static void get1(){
        byte[] dArray = new byte[10];
        dArray[0] = 1;

        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        buffer.put((byte) 10);
        buffer.put((byte) 11);

        buffer.flip();

        buffer.get(dArray, 1, 1);
        //[1, 10, 0, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(Arrays.toString(dArray));
    }

    private static void get2(){
        byte[] dArray = new byte[10];
        dArray[0] = 1;

        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        buffer.put((byte) 10);
        buffer.put((byte) 11);

        buffer.flip();

        buffer.get(dArray, 2, 1);
        //[1, 0, 10, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(Arrays.toString(dArray));
    }

    private static void get3(){
        byte[] dArray = new byte[10];
        dArray[0] = 1;

        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        buffer.put((byte) 10);
        buffer.put((byte) 11);

        //buffer.flip();

        buffer.get(dArray, 2, 1);
        //[1, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(Arrays.toString(dArray));
    }
}
