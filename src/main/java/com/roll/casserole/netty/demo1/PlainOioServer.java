package com.roll.casserole.netty.demo1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * OIO实例
 * @author zongqiang.hao
 * created on 2019-04-16 21:44.
 */
public class PlainOioServer {
    public void server(int port) throws IOException {
        //绑定服务器到指定的端口
        final ServerSocket socket = new ServerSocket(port);
        try {
            for (; ; ) {
                //新接受一个连接
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream out;
                        try {
                            //将消息发送到连接的客户端,并进行写入和刷新
                            out = clientSocket.getOutputStream();
                            out.write("HI!\r\n".getBytes(Charset.forName("utf-8")));
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                            try {
                                clientSocket.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
