import java.util.*;

class Multiplication extends Problem {
    
    public Multiplication(int max) {
    	super(max);
		int a = new Random().nextInt(max);
		int b = new Random().nextInt(max);
		setPrompt(new String(a + " * " + b + " = ?"));
		setAnswer(new String(Integer.toString(a * b)));
    }

    public Problem next() {
    	return new Multiplication(max);
    }

    public String getTitle() {
    	return "Multiplication to " + max;
    }
}
