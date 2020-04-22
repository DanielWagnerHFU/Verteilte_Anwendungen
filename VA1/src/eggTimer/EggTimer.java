package eggTimer;

public class EggTimer {
	
	public static void StartEggTimer(String text, int milliseconds) {
		EggTimerThread eggTimer = new EggTimerThread(text, milliseconds);
		eggTimer.start();
	}
}
