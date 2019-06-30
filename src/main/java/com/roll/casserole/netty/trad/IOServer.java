package com.roll.casserole.netty.trad;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zongqiang.hao
 * created on 2018/11/21 7:42 PM.
 */
public class IOServer {
    public static void main(String args[]) {

        ServerSocket serverSocket;

        {
            try {
                serverSocket = new ServerSocket(8000);
                new Thread(() -> {
                    while (true) {
                        try {
                            Socket socket = serverSocket.accept();
                            new Thread(() -> {
                                try {
                                    int len;
                                    byte[] data = new byte[1024];
                                    InputStream inputStream = socket.getInputStream();
                                    //按照字节流方式读取数据
                                    while ((len = inputStream.read(data)) != -1) {
                                        System.out.println(new String(data, 0, len));
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }).start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
