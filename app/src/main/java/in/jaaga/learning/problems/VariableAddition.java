package in.jaaga.learning.problems;

import java.util.*;

import in.jaaga.learning.Problem;

public class VariableAddition extends SimpleProblem {
	int max;

    public VariableAddition(int max) {
		this.max = max;
		int a = new Random().nextInt(max);
		int b = new Random().nextInt(max);
		if (new Random().nextInt(2) % 2 == 0) {
			prompt = new String(a + " + ? = " + (a+b));
		} else {
			prompt = new String("? + " + a + " = " + (a+b));
    	}
		answer = new String(Integer.toString(b));
    }

    public Problem next(){
		return new VariableAddition(max);
	}

    public String getTitle() {
    	return "Addition with variables upto " + max;
    }
}
