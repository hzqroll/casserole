package com.roll.casserole.nio;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>@author roll
 * <p>created on 2020/7/24 8:39 上午
 */
public class FdTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(FileDescriptor.in);
        FileOutputStream fileOutputStream = new FileOutputStream(FileDescriptor.out);
        byte[] a = new byte[100];
        fileInputStream.read(a);
        fileOutputStream.write(a);
        fileOutputStream.close();
    }
}
