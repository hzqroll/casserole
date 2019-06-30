package com.roll.casserole.netty.nio;

import java.io.*;
import java.net.Socket;

/**
 * 祖塞式IO
 *
 * @author zongqiang.hao
 * created on 2018/9/16 下午8:41.
 */
public class Client {
    private static int DEFAULE_SERVER_PORT = 9010;
    private static String DEFAULT_SErVER_IP = "127.0.0.1";

    public Client(String expression) {

    }

    public static void send(int port, String expression) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(DEFAULT_SErVER_IP, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            out.println(expression);
            System.out.println("结果为： " + in.readLine());
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
            if (out != null) {
                out.close();
                out = null;
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
