package com.roll.casserole.jvm.bytecode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

/**
 * <p>@author roll
 * <p>created on 2020/8/2 3:11 下午
 */
public class MyTest3 {
    public void test() {
        try {
            InputStream i = new FileInputStream("");

            ServerSocket serverSocket = new ServerSocket();
            serverSocket.accept();
        } catch (FileNotFoundException fileNotFoundException) {

        } catch (IOException ioException) {

        } catch (Exception e) {

        } finally {
            System.out.println("finally");
        }
    }
}
