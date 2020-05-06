package main;

public class TimeServiceClientThread extends Thread {
	private String _ip;
	private int _port;
	private long _sleepMillis;
	
	public TimeServiceClientThread (String ip, int port, long sleepMillis) {
		_ip = ip;
		_port = port;
		_sleepMillis = sleepMillis;
	}

	@Override
	public void run() {
		while(true) {
			System.out.println(this.toString() + " - " + TimeServiceClient.dateFromServer(_ip, _port));
			System.out.println(this.toString() + " - " + TimeServiceClient.timeFromServer(_ip, _port));
			try {
				Thread.sleep(_sleepMillis);
			} catch (InterruptedException e) {}
		}
	}
}
