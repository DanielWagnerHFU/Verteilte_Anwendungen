package timeservice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeService extends Thread{
	private Socket _socket;
	private ServerSocket _serverSocket;
	private BufferedReader _reader;
	private BufferedWriter _writer;
	
	public TimeService(int port) throws IOException {
		_serverSocket = new ServerSocket(port);
	}
	
	private void runTimeService() throws IOException {
		String outputMessage = _reader.readLine();
		switch (outputMessage) {
		case "time":
			_writer.newLine();
			writeToClient(getTime());
			break;
		case "date":
			_writer.newLine();
			writeToClient(getDate());
			break;
		default:
			deinitializeSocket();
			initializeSocket();
			break;
		}
		runTimeService();
	}
	
	private void deinitializeSocket() throws IOException {
		_writer.close();
		_reader.close();
		_socket.close();
	}
	
	private String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
	}
	
	private String getTime() {
		return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date());
	}
	
	private void writeToClient(String outputMessage) throws IOException {
		_writer.write(outputMessage);
		_writer.newLine();
		_writer.flush();
	}
	
	private void initializeSocket() throws IOException {
		_socket = _serverSocket.accept();
		_writer = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream()));;
		_reader = new BufferedReader(new InputStreamReader(_socket.getInputStream()));;
		writeToClient("timeservice");
		
	}

	@Override
	public void run() {
		try {
			while(true) {
				initializeSocket();
				runTimeService();			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
