package com.roll.casserole.netty.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zongqiang.hao
 * created on 2019-06-29 21:28.
 */
public class NewIOClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        socketChannel.configureBlocking(true);

        String filwName = "/Users/roll/Downloads/soft/wiznote-macos-2019-01-18.dmg";

        FileChannel fileChannel = new FileInputStream(filwName).getChannel();

        long startTime = System.currentTimeMillis();

        long transarCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送字节数： " + transarCount + ", 耗时： " + (System.currentTimeMillis() - startTime));

        fileChannel.close();

    }
}
