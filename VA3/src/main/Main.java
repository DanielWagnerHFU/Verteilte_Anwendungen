package main;

public class Main {

	public static void main(String[] args) {
		TimeServiceServerThread server = new TimeServiceServerThread(8888);
		server.start();
		
		//Generate Fake Client Threads - writing to output stream
		for(int i = 0; i < 1000; i++) {
			TimeServiceClientThread client = new TimeServiceClientThread("127.0.0.1", 8888, 1000);
			client.start();				
		}
	}
}
