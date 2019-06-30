package com.roll.casserole.netty.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端线程
 *
 * @author zongqiang.hao
 * created on 2018/9/16 上午9:57.
 */
public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            out = new PrintWriter(socket.getOutputStream());
            String expression;
            String result;
            while (true) {
                if ((expression = in.readLine()) == null) {
                    break;
                }
                System.out.println("服务器收到消息：" + expression);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
