import java.util.*;

class SimpleSubtraction extends Problem {
    public SimpleSubtraction(int max) {
		int a = new Random().nextInt(max);
		int b = new Random().nextInt(a + 1);
		setPrompt(new String(a + " - " + b + " = ?"));
		setAnswer(new String(Integer.toString(a - b)));
    }
}
