package in.jaaga.learning.problems;

import java.util.*;

import in.jaaga.learning.Problem;

public class VariableSubtraction extends Problem {
    int max;
    public VariableSubtraction(int max) {
		this.max = max;
		int a = new Random().nextInt(max);
        int b = new Random().nextInt(a + 1);
		if (new Random().nextInt(2) % 2 == 0) {
			setPrompt(new String(a + " - ? = " + (a-b)));
            setAnswer(new String(Integer.toString(b)));
		} else {
			setPrompt(new String("? - " + b + " = " + (a-b)));
 	        setAnswer(new String(Integer.toString(a)));
        }
    }

    public Problem next(){
        return new VariableSubtraction(max);
    }

    public VariableSubtraction(int max1, int max2) {
    	this(max1);
    }

    public String getTitle() {
    	return "Subtraction with variables upto " + max;
    }
}
