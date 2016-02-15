package in.jaaga.learning.problems;

import java.util.*;

import in.jaaga.learning.Problem;

public class DecimalMultiplication extends SimpleProblem {

    int max, min, digitsNumber;
    double a, b, ab, answer;
    ArrayList<String> answerList = new ArrayList<String>();

    public DecimalMultiplication(int max, int min, int digitsNumber) {
        this.max = max;
        this.min = min;
        this.digitsNumber = digitsNumber;

        a = new Random().nextDouble() * (max - min) + min;
        b = new Random().nextDouble() * (max - min) + min;
        ab = a * b;
        double roundingMultiplier = Math.pow(10.0, (double)digitsNumber);
        answer = Math.round(ab * roundingMultiplier) / roundingMultiplier;
        prompt = a + " * " + b + " = ?";
    }

    /*
    public String getPrompt() {
        return a + " x " + b + " = ?";
    }
    */

    public boolean checkAnswer(String ans) { return (Double.parseDouble(ans) == answer); }

    public String getHint() { return Double.toString(answer); }

    public Problem next() {
        return new DecimalMultiplication(max, min, digitsNumber);
    }

    public String getTitle() {
        return "Decimal multiplication with variables up to " + max;
    }
}
