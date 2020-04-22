package dispatcher;

public class Result {
	
	private int[] _result;
	private int _storedResults = 0;
	
	public Result(int n) {
		_result = new int[n];
	}
	
	public synchronized int[] getResult() throws InterruptedException {
		System.out.println("requested");
		while(_storedResults != _result.length) {
			wait();
		}
		return _result;
	}
	
	public synchronized void setParialResultAt(int x, int y) {
		_result[x] = y;
		_storedResults++;
		notify();
	}
}
