package com.roll.casserole.netty.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO服务端
 */
public class BIOServer {

    private static ExecutorService executorService = Executors.newFixedThreadPool(100);

    private static int DEFAULT_PORT = 9010;

    private static ServerSocket serverSocket;

    //根据入参设置监听端口，如果没有参数使用默认值
    public static void start() throws IOException {
        start(DEFAULT_PORT);
    }

    /**
     * @param port
     * @throws IOException
     */
    public synchronized static void start(int port) throws IOException {
        if (serverSocket != null) {
            return;
        }
        try {
            //通过够赞函数创建ServerSocket，如果端口合法，服务端监听成功
            serverSocket = new ServerSocket(port);
            System.out.println("服务启动，端口号：" + port);
            //通过循环监听客户端连接，如果没有客户端接入，将阻塞在accept操作上
            while (true) {
                Socket socket = serverSocket.accept();
                //当有新的客户端接入时，会执行下面的代码，然后创建一个新的线程吹这条socket链路
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                System.out.println("服务器关闭。");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
