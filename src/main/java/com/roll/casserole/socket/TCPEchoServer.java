package com.roll.casserole.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * 建立一个服务器端，
 *
 * @author haozq
 * Date: 2018/8/13 上午10:37
 */
public class TCPEchoServer {
	private static final int BUFSIZE = 32;

	/**
	 * ServerSocket实例的唯一目的，视为新的TCP连接请求提供一个新的已连接的socket实例
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		int port = Integer.parseInt(args[0]);
		ServerSocket serverSocket = new ServerSocket(port);
		int recvMsgSize;
		byte[] receiveBuf = new byte[BUFSIZE];
		while (true) {
			Socket socket = serverSocket.accept();
			SocketAddress clientAddress = socket.getRemoteSocketAddress();
			System.out.println("Handling client at: " + clientAddress);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			while ((recvMsgSize = inputStream.read(receiveBuf)) != -1) {
				outputStream.write(receiveBuf, 0, recvMsgSize);
				System.out.println(outputStream.toString());
			}
			socket.close();
		}
	}
}
