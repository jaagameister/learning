import java.util.*;

class Addition extends Problem {

    public Addition(int max) {
		super(max);
		int a = new Random().nextInt(max);
		int b = new Random().nextInt(max);
		setPrompt(new String(a + " + " + b + " = ?"));
		setAnswer(new String(Integer.toString(a + b)));
    }

    public Problem next() {
    	return new Addition(max);
    }

    public String getTitle() {
    	return "Simple Addition to " + max;
    }
}

