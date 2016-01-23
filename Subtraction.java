import java.util.*;

class Subtraction extends Problem {

    public Subtraction(int max) {
    	init(max);
    	int a = new Random().nextInt(max);
		int b = new Random().nextInt(a + 1);
		setPrompt(new String(a + " - " + b + " = ?"));
		setAnswer(new String(Integer.toString(a - b)));
    }

    public Subtraction(int max1, int max2) {
        this(max1);
    }

    public String getTitle() {
        return "Subtraction to " + max1;
    }


}
