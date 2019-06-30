package com.roll.casserole.netty.buffer;

import java.io.*;

/**
 * @author zongqiang.hao
 * created on 2018/10/8 下午3:24.
 */
public class StreamTest {
    public static void input() throws IOException {
        File file = new File("/Users/roll/Desktop/temp/indi-format.txt");
        FileInputStream stream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String s = bufferedReader.readLine();
        while (s != null) {
            System.out.println(s);
            s = bufferedReader.readLine();
        }
    }

    public static void output() throws IOException {
        File file = new File("/Users/roll/Desktop/temp/indi-format.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String str = "hello world";
        byte[] b = str.getBytes();
        bufferedOutputStream.write(b);
        bufferedOutputStream.flush();
    }

    public static void main(String args[]) throws IOException {
        output();
        input();
    }
}
