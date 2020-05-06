package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TimeServiceClient {
	
	public synchronized static String dateFromServer(String ip, int port) {
		return requestResponseWithServer("date", ip, port);
	}
	
	public synchronized static String timeFromServer(String ip, int port) {
		return requestResponseWithServer("time", ip, port);
	}
	
	private synchronized static String requestResponseWithServer(String request, String ip, int port) {
		try {
			Socket socket = new Socket(ip, port);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer.write(request); writer.newLine(); writer.flush();
			reader.readLine();
			String response = reader.readLine();
			writer.close(); reader.close(); socket.close();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return ":::Socket failed:::";
		}
	}
}
