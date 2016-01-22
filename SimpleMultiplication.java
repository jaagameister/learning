import java.util.*;

class SimpleMultiplication extends Problem {
    
    public SimpleMultiplication(int max) {
    	super(max);
		int a = new Random().nextInt(max);
		int b = new Random().nextInt(max);
		setPrompt(new String(a + " * " + b + " = ?"));
		setAnswer(new String(Integer.toString(a * b)));
    }

    public Problem next() {
    	return new SimpleMultiplication(max);
    }
}
