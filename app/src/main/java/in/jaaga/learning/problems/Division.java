package in.jaaga.learning.problems;

import java.util.*;

import in.jaaga.learning.Problem;

public class Division extends Problem {
    int dividendMax, divisorMax;

    public Division(int dividendMax, int divisorMax) {
        this.dividendMax = dividendMax;
        this.divisorMax = divisorMax;

        int quotientMax =  dividendMax / divisorMax;
        int b = new Random().nextInt(divisorMax) + 1;
        int a = b * new Random().nextInt(quotientMax);
        setPrompt(new String(a + " / " + b + " = ?"));
        setAnswer(new String(Integer.toString(a / b)));
    }

    public Problem next(){
        return  new Division(dividendMax, divisorMax);
    }

    public String getTitle() {
        return "Division with dividend to " + dividendMax +
                " and divisor to " + divisorMax;
    }


}
