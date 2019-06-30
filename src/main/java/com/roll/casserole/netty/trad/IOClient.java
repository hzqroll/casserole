package com.roll.casserole.netty.trad;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author zongqiang.hao
 * created on 2018/11/21 7:50 PM.
 */
public class IOClient {
    public static void main(String args[]){
        new Thread(()->{
            try {
                Socket socket = new Socket("127.0.0.1", 8001);
                while (true){
                    socket.getOutputStream().write((new Date() + ":hello").getBytes());
                    Thread.sleep(1000);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
