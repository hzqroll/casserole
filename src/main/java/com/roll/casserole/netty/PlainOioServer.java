package com.roll.casserole.netty;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 未使用Netty的阻塞网络编程
 *
 * @author haozq
 * Date: 2018/8/12 下午12:15
 */
public class PlainOioServer {
	public void server(int port) throws IOException {
		final ServerSocket socket = new ServerSocket(port);
		try {
			for (; ; ) {
				final Socket clientSocket = socket.accept();
				System.out.println("Accepted connetcion from " + clientSocket);
				new Thread(() -> {
					OutputStream out;
					try {
						out = clientSocket.getOutputStream();
						out.write("HI!\r\n".getBytes(Charset.forName("UTF-8")));
						out.flush();
						clientSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							clientSocket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
