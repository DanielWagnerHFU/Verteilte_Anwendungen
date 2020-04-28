package main;

import java.io.IOException;

import timeservice.TimeService;

public class Main {

	public static void main(String[] args) {
		try {
			TimeService timeservice = new TimeService(8888);
			timeservice.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
