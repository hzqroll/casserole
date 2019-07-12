package com.roll.casserole.nio.scalable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author zongqiang.hao
 * created on 2019-07-09 16:26.
 */
public class BaseClient {
    public static void main(String[] args) throws IOException {
        channel();
    }


    public static void socket() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(9004));

        byte[] send = new byte[10];
        send[0] = 1;
        send[1] = 2;
        send[2] = 3;
        send[3] = 4;

        socket.getOutputStream().write(send);
    }


    public static void channel() throws IOException {
        SocketChannel socket = SocketChannel.open();
        socket.connect(new InetSocketAddress(9004));

        ByteBuffer writeBuffer = ByteBuffer.allocate(10);
        writeBuffer.put((byte) 1);
        writeBuffer.put((byte) 2);
        writeBuffer.flip();
        socket.write(writeBuffer);
    }
}
