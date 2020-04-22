package dispatcher;

public class Factorial implements IFunction{
	
	@Override
	public int f(int x) {
		if (x > 11) throw new IllegalArgumentException(x + " is out of range");
		return (1 > x) ? 1 : x * f(x - 1);
	}
}
