import java.util.*;

class Addition extends Problem {

    public Addition(int max) {
		init(max);
		int a = new Random().nextInt(max);
		int b = new Random().nextInt(max);
		setPrompt(new String(a + " + " + b + " = ?"));
		setAnswer(new String(Integer.toString(a + b)));
    }

    public Addition(int max1, int max2) {
    	this(max1);
    }

    public String getTitle() {
    	return "Simple Addition to " + max1;
    }
}

