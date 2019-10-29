package com.roll.casserole.nio.channel;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author roll
 * created on 2019-10-21 17:26
 */
public class FileDescriptorTest1 {
    public static void main(String[] args) throws IOException {

        FileOutputStream fileInputStream = new FileOutputStream(FileDescriptor.out);
        fileInputStream.write("hello world".getBytes());
        fileInputStream.close();
    }
}
