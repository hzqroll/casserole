package com.roll.casserole.nio.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>@author roll
 * <p>created on 2020/9/22 11:24 上午
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        while (!Thread.interrupted()) {
            Socket socket = serverSocket.accept();
            System.out.println(String.format("收到客户端[ip: %s], [port: %s]连接",
                    socket.getInetAddress().getHostName(), socket.getPort()));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            new Thread(() -> {
                try {
                    while (true) {
                        String mes = reader.readLine();
                        System.out.println(mes);

                        if (mes == null)
                            break;
                    }
                } catch (IOException ignored) {
                }
            }).start();
        }
    }
}
