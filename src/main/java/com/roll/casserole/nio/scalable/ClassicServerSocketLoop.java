package com.roll.casserole.nio.scalable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ClassicServerSocketLoop implements Runnable {

    public ClassicServerSocketLoop(int port) {
        this.port = port;
    }

    private int port;

    @Override
    public void run() {
        try {
            // 新建一个servetSocket来接受客户端的连接, 绑定到指定的接口
            ServerSocket serverSocket = new ServerSocket(port);
            while (!Thread.interrupted()) {
                // 新建线程去处理连接，server.accept()此方法是阻塞方法，监听此 socket 上面的链接，并且接受， 返回一个 socket。
                new Thread(new Handler(serverSocket.accept())).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handler来处理accept事件
    static class Handler implements Runnable {

        final Socket socket;

        Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                byte[] input = new byte[100];
                // 从socket中获取输入流
                int count = socket.getInputStream().read(input);
                System.out.println("count: " + count + ", " + (new String(input)));
                // 返回给socket的数据，通常process在此处理， process()
                byte[] output = process();
                socket.getOutputStream().write(output);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 处理程序
    private static byte[] process() {
        return new byte[100];
    }

    public static void main(String[] args) throws IOException {
        // 新建一个servetSocket来接受客户端的连接, 绑定到指定的接口
        ServerSocket serverSocket = new ServerSocket(9004);
        // 新建线程去处理连接，server.accept()此方法是阻塞方法，监听此 socket 上面的链接，并且接受， 返回一个 socket。
        while (true) {
            Socket socket = serverSocket.accept();
            byte[] input = new byte[100];
            // 从socket中获取输入流
            int count = socket.getInputStream().read(input);
            System.out.println("count: " + count + ", " + Arrays.toString(input));
            // 返回给socket的数据，通常process在此处理， process()
            byte[] output = process();
            socket.getOutputStream().write(output);
        }
    }
}
