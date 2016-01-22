import java.util.*;

class SimpleAddition extends Problem {

    public SimpleAddition(int max) {
		super(max);
		int a = new Random().nextInt(max);
		int b = new Random().nextInt(max);
		setPrompt(new String(a + " + " + b + " = ?"));
		setAnswer(new String(Integer.toString(a + b)));
    }

    public Problem next() {
    	return new SimpleAddition(max);
    }

    public String getTitle() {
    	return "Simple Addition to " + max;
    }
}

