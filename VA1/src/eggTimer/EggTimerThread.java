package eggTimer;

public class EggTimerThread extends Thread {
	
	private String _text;
	private int _milliseconds;
	
	public EggTimerThread(String text, int milliseconds) {
		_text = text;
		_milliseconds = milliseconds;
	}
	
	@Override
	public void run() {
		while(_milliseconds >= 0) {
			System.out.println(this.toString());
			waitOneSecond();
		}
		System.out.println("Timer terminated at 0");
	}
	
	@Override
	public String toString() {
		return _text + _milliseconds;
	}
	
	private void waitOneSecond() {
		try {
			Thread.sleep(1000);
			_milliseconds -= 1000;
		} catch (InterruptedException e) {}
	}
}
