package dispatcher;

public class Dispatcher {
	
	public static int[] execute(IFunction f, int n) throws InterruptedException {
		Result result = new Result(n);
		for(int x = 0; x < n; x++) {
			SingleCalculationThread calculation = new SingleCalculationThread(f, x, result);
			calculation.start();
		}
		return result.getResult();
	}
}
