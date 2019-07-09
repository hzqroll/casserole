package com.roll.casserole.nio.scalable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author zongqiang.hao
 * created on 2019-07-09 16:26.
 */
public class BaseClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(9004));

        byte[] send = new byte[10];
        send[0] = 1;
        send[1] = 2;
        send[2] = 3;
        send[3] = 4;

        socket.getOutputStream().write(send);
    }
}
