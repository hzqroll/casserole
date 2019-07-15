package com.roll.casserole.nio.simple;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author zongqiang.hao
 * created on 2019-07-15 20:21.
 */
public class SimpleServer {
    public static void main(String[] args) throws IOException {
        // 新建一个servetSocket来接受客户端的连接, 绑定到指定的接口
        ServerSocket serverSocket = new ServerSocket(9004);
        // 新建线程去处理连接，server.accept()此方法是阻塞方法，监听此 socket 上面的链接，并且接受， 返回一个 socket。
        while (true) {
            Socket socket = serverSocket.accept();
            byte[] input = new byte[10];
            // 从socket中获取输入流
            int count = socket.getInputStream().read(input);
            System.out.println("count: " + count + ", " + Arrays.toString(input));
        }
    }
}
