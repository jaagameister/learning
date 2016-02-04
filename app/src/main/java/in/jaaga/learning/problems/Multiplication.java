package in.jaaga.learning.problems;

import java.util.*;

import in.jaaga.learning.Problem;

public class Multiplication extends Problem {
    int factor1, factor2;

    public Multiplication(int factor1, int factor2) {
    	this.factor1 = factor1;
        this.factor2 = factor2;

		int a = new Random().nextInt(Math.abs(factor1));
		int b = new Random().nextInt(Math.abs(factor2));
    	if (factor1 < 0) {
			a -= Math.abs(factor1/2);
			b -= Math.abs(factor2/2);
		}
		setPrompt(new String(a + " * " + b + " = ?"));
		setAnswer(new String(Integer.toString(a * b)));
    }

    public Problem next(){
        return new Multiplication(factor1, factor2);
    }

    public String getTitle() {
        int m = Math.abs(factor1);
        String title = "Multiplication to " + factor1;
        if (factor1 > 0)
            return title;
        return title + " with negative numbers";
    }
}
