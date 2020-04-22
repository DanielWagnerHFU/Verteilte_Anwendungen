package main;

import java.util.Arrays;
import java.util.Scanner;

import dispatcher.Dispatcher;
import eggTimer.EggTimer;
import dispatcher.*;

public class Main {

	public static void main(String[] args) {
		//TestEggTimer();
		TestDispatcher();
	}
	
	public static void TestEggTimer() {
		Scanner myInput = new Scanner( System.in );
		System.out.print( "Enter time to wait in milliseconds: " );
		int millisecondsToWait = myInput.nextInt();
		EggTimer.StartEggTimer("Timer at: ", millisecondsToWait);		
		myInput.close();		
	}
	
	public static void TestDispatcher() {
		Scanner myInput = new Scanner( System.in );
		System.out.print( "Enter the amout of Threads: " );
		int n = myInput.nextInt();
		IFunction f = new Factorial();
		int[] results = null;
		try {
			results = Dispatcher.execute(f, n);
		} catch (InterruptedException e) {}	
		System.out.println("Result: " + Arrays.toString(results));
		myInput.close();
	}
}
