package in.jaaga.learning.bots.skillbot.problems;

//import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import in.jaaga.learning.bots.skillbot.Problem;

/**
 * Created by root on 6/2/16.
 */
public class DecimalDivision extends SimpleProblem {
    int dividendMax,divisorMax;
    ArrayList<String> answerList = new ArrayList<String>();
    double answer;
    int hintNumber = 0;

    public DecimalDivision(int dividendMax, int divisorMax) {
        this.dividendMax = dividendMax;
        this.divisorMax = divisorMax;
        //Log.d("dividend",Integer.toString(divisorMax));
        int quotient = dividendMax/divisorMax;
        int b = new  Random().nextInt(divisorMax) + 1;
        int a = new Random().nextInt(dividendMax) + 1;
        Integer aa = new Integer(a);
        Integer bb = new Integer(b);
        double a_double = aa.doubleValue();
        double b_double = bb.doubleValue();
        double c = a_double/b_double;
        answer = Math.round(c*1000.0)/1000.0;
        prompt = new String(a + " ÷ " + b +" = ? \n(ie. 3.25 round to 3 decimal places)");
    }
    public boolean checkAnswer(String ans) {
        try {
            return (answer == Double.parseDouble(ans));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getHint() {
        if (hintNumber++ == 1)
            return "should be a number like 3.25 round to 3 decimal places";
        return Double.toString(answer);
    }

    public Problem next() {
        return new DecimalDivision(dividendMax,divisorMax);
    }

    public String getTitle() {
        return  "Decimal division with dividend to " + dividendMax +
                " and divisor to " + divisorMax + "round answers to 3 decimal places";
    }
}
