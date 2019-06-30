package com.roll.casserole.netty.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author zongqiang.hao
 * created on 2019-06-29 15:03.
 */
public class OldClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8899);

        String filwName = "/Users/roll/Downloads/soft/wiznote-macos-2019-01-18.dmg";

        InputStream inputStream = new FileInputStream(filwName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        int readCount;
        int total = 0;
        long startTime = System.currentTimeMillis();
        while ((readCount = inputStream.read(buffer)) >= 0) {
            total += readCount;

            dataOutputStream.write(buffer);
        }

        System.out.println("发送字节数： " + total + ", 耗时： " + (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        inputStream.close();
        socket.close();
    }
}
