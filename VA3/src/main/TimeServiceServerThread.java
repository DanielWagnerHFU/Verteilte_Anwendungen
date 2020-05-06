package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServiceServerThread extends Thread {
	private int _port;
	
	public TimeServiceServerThread(int port) {
		_port = port;
	}
	@Override
	public void run() {
		try(ServerSocket serverSocket = new ServerSocket(_port)) {
			while(!serverSocket.isClosed()) {
				Socket socket = serverSocket.accept();
				TimeServiceConnectionThread timeServiceConnection = new TimeServiceConnectionThread(socket, false);
				timeServiceConnection.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
