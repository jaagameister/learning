package in.jaaga.learning.bots.srini.problems;

import java.util.*;

import in.jaaga.learning.bots.srini.Problem;

public class VariableDivision extends SimpleProblem {
    int dividendMax,divisorMax;

    public VariableDivision(int dividendMax, int divisorMax) {
        this.dividendMax = dividendMax;
        this.divisorMax = divisorMax;
        int quotientMax =  dividendMax / divisorMax;
        int b = new Random().nextInt(divisorMax) + 1;
        int a = b * (new Random().nextInt(quotientMax) + 1);
        if (new Random().nextInt(2) % 2 == 0) {
            prompt = (new String(a + " ÷ ? = " + (a/b)));
            answer = (new String(Integer.toString(b)));
        } else {
            prompt = (new String("? ÷ " + b + " = " + (a/b)));
            answer = (new String(Integer.toString(a)));
        }
    }

    public Problem next(){
        return new VariableDivision(dividendMax, divisorMax);
    }

    public String getTitle() {
        return "Division with dividend to " + dividendMax +
                " and divisor to " + divisorMax;
    }


}
