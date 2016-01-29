package in.jaaga.learning.cli;

import java.util.*;

class VariableDivision extends Problem {

    public VariableDivision(int dividendMax, int divisorMax) {
        init(dividendMax, divisorMax);
        int quotientMax =  dividendMax / divisorMax;
        int b = new Random().nextInt(divisorMax) + 1;
        int a = b * (new Random().nextInt(quotientMax) + 1);
        if (new Random().nextInt(2) % 2 == 0) {
            setPrompt(new String(a + " / ? = " + (a/b)));
            setAnswer(new String(Integer.toString(b)));
        } else {
            setPrompt(new String("? / " + b + " = " + (a/b)));
            setAnswer(new String(Integer.toString(a)));            
        }
    }

    public String getTitle() {
        return "Division with dividend to " + max1 + 
                " and divisor to " + max2;
    }


}
