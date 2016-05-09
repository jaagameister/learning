package in.jaaga.learning.bots.skillbot.problems;

import java.util.*;

import in.jaaga.learning.bots.skillbot.Problem;

public class Multiplication extends SimpleProblem {
    int factor1, factor2;
    int a, b;

    public Multiplication(int factor1, int factor2) {
    	this.factor1 = factor1;
        this.factor2 = factor2;

		a = new Random().nextInt(Math.abs(factor1));
		b = new Random().nextInt(Math.abs(factor2));
        if (factor1 < 0) a *= -1;
        if (factor2 < 0) b *= -1;

        prompt = a + " x " + b + " = ?";
        answer = Integer.toString(a * b);
    }

    public Problem next(){
        return new Multiplication(factor1, factor2);
    }
}
