import java.util.*;

class Multiplication extends Problem {
    
    public Multiplication(int factor1, int factor2) {
    	init(factor1, factor2);
		int a = new Random().nextInt(max1);
		int b = new Random().nextInt(max2);
		setPrompt(new String(a + " * " + b + " = ?"));
		setAnswer(new String(Integer.toString(a * b)));
    }

    public String getTitle() {
    	return "Multiplication to " + max1;
    }
}
