package in.jaaga.learning.problems;

import java.util.*;

import in.jaaga.learning.Problem;

public class VariableMultiplication extends SimpleProblem {
    int factor1, factor2;

    public VariableMultiplication(int factor1, int factor2) {
        this.factor1 = factor1;
        this.factor2 = factor2;
		int a = new Random().nextInt(factor1) + 1;
		int b = new Random().nextInt(factor2) + 1;
        if (new Random().nextInt(2) % 2 == 0) {
    		prompt = (new String(a + " x ? = " + (a*b)));
            answer = (new String(Integer.toString(b)));
        } else {
            prompt = (new String("? x " + b + " = " + (a*b)));
            answer = (new String(Integer.toString(a)));

        }
    }

    public Problem next(){
        return new Multiplication(factor1, factor2);
    }

    public String getTitle() {
    	return "Variable Multiplication to " + factor1;
    }
}
