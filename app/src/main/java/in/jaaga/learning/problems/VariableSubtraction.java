package in.jaaga.learning.problems;

import java.util.*;

import in.jaaga.learning.Problem;

public class VariableSubtraction extends SimpleProblem {
    int max;

    public VariableSubtraction(int max) {
		this.max = max;
		int a = new Random().nextInt(max);
        int b = new Random().nextInt(a + 1);
		if (new Random().nextInt(2) % 2 == 0) {
			prompt = (new String(a + " - ? = " + (a-b)));
            answer = (new String(Integer.toString(b)));
		} else {
			prompt = (new String("? - " + b + " = " + (a-b)));
 	        answer = (new String(Integer.toString(a)));
        }
    }

    public Problem next() {
        return new VariableSubtraction(max);
    }

    public String getTitle() {
    	return "Subtraction with variables upto " + max;
    }
}
