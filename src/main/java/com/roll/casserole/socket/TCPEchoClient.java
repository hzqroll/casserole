package com.roll.casserole.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author haozq
 * Date: 2018/8/13 上午10:20
 */
public class TCPEchoClient {
	public static void main(String args[]) throws IOException {
		String server = args[0];
		int port = Integer.parseInt(args[1]);
		byte[] data = args[2].getBytes();
		Socket socket = new Socket(server, port);

		InputStream inputStream = socket.getInputStream();
		OutputStream outputStream = socket.getOutputStream();

		outputStream.write(data);
		int totalByteRcvd = 0;
		int bytesRcvd;
		while (totalByteRcvd < data.length) {
			if ((bytesRcvd = inputStream.read(data, totalByteRcvd, data.length - totalByteRcvd)) == -1) {
				System.out.println("Connection closed prematurely");
				break;
			}
			totalByteRcvd += bytesRcvd;
		}
		System.out.println("Received: " + new String(data));
		socket.close();
	}
}
