package in.jaaga.learning.cli;
import java.util.*;

class VariableMultiplication extends Problem {
    
    public VariableMultiplication(int factor1, int factor2) {
    	init(factor1, factor2);
		int a = new Random().nextInt(max1) + 1;
		int b = new Random().nextInt(max2) + 1;
        if (new Random().nextInt(2) % 2 == 0) {
    		setPrompt(new String(a + " * ? = " + (a*b)));
            setAnswer(new String(Integer.toString(b)));
        } else {
            setPrompt(new String("? * " + b + " = " + (a*b)));
            setAnswer(new String(Integer.toString(a)));

        }
    }

    public String getTitle() {
    	return "Variable Multiplication to " + max1;
    }
}
