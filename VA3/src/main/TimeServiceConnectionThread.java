package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeServiceConnectionThread extends Thread {
	private Socket _socket;
	private String _name = "TIMESERVICE";
	private boolean _debug;
	
	public TimeServiceConnectionThread(Socket socket, boolean debugOn) {
		_socket = socket;
		_debug = debugOn;
	}

	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream()));
			writeToClient(writer, "successful connection - use 'date' or 'time' to request the server date or time");
			writeDebugMessage("connected");
			runTimeService(reader, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void runTimeService(BufferedReader reader, BufferedWriter writer) throws IOException {
		while(!_socket.isClosed()) {
			String clientCommand = reader.readLine();
			if(clientCommand == null) {
				closeConnection(reader, writer);
			} else {		
				switch (clientCommand) {
				case "time":
					writeToClient(writer, "Time = " + getTime());
					break;
				case "date":
					writeToClient(writer, "Date = " + getDate());
					break;
				default:
					closeConnection(reader, writer);
					break;
				}
			}
		}
	}
	
	private void closeConnection(BufferedReader reader, BufferedWriter writer) throws IOException {
		writeToClient(writer, "Exception = 'illegal argument', Handling = 'closing connection'");
		writer.close();
		reader.close();
		writeDebugMessage("disconnected");
		_socket.close();
	}
	
	private String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
	}
	
	private String getTime() {
		return new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date());
	}
	
	private void writeToClient(BufferedWriter writer, String outputMessage) throws IOException {
		writer.write(_name + " :: " + outputMessage);
		writer.newLine();
		writer.flush();
	}
	
	private void writeDebugMessage(String message) {
		if(_debug) {
			System.err.println(this.toString() + ", IP =" + _socket.getInetAddress().toString() + " " + message);
		}
	}
}
