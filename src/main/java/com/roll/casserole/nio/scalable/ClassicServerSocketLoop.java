package com.roll.casserole.nio.scalable;

import java.net.ServerSocket;
import java.net.Socket;

public class ClassicServerSocketLoop implements Runnable {

    private int port;

    @Override
    public void run() {
        try {
          // 新建一个servetSocket来接受客户端的连接
            ServerSocket serverSocket = new ServerSocket(port);
            while (Thread.interrupted()){
                // 新建线程去处理连接
                new Thread(new Handler(serverSocket.accept())).start();
            }
        } catch (Exception e){

        }
    }

    // Handler来处理accept事件
    static class Handler implements Runnable{

        final Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                byte[] input = new byte[100];
                // 从socket中获取输入流
                socket.getInputStream().read(input);

                // 返回给socket的数据，通常process在此处理， process()
                byte[] output = process();
                socket.getOutputStream().write(output);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 处理程序
    private static byte[] process(){
        return new byte[100];
    }
}
