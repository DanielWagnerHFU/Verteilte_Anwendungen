package dispatcher;

public class SingleCalculationThread extends Thread{
	
	private IFunction _function;
	private int _x;
	private Result _resultStorage;
	
	public SingleCalculationThread(IFunction function, int x, Result resultStorage) {
		_function = function;
		_x = x;
		_resultStorage = resultStorage;
	}
	
	@Override
	public void run() {
		int y = _function.f(_x);
		_resultStorage.setParialResultAt(_x, y);
	}
}
