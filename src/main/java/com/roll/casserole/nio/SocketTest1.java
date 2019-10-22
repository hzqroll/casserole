package com.roll.casserole.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author roll
 * created on 2019-10-19 19:05
 */
public class SocketTest1 {
    public static void main(String[] args) throws IOException {
        InetAddress address1 = InetAddress.getByName("127.0.0.1");


        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress(9011);
        socket.connect(address,  1000000);

        System.out.println("12");
    }
}
