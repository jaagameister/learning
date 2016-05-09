package in.jaaga.learning.bots.skillbot.problems;

import java.util.*;
import java.math.*;

import in.jaaga.learning.bots.skillbot.Problem;

public class DecimalMultiplication extends SimpleProblem {
    int max1, places1, max2, places2;
    BigDecimal a, b, ab;
    double ans;

    /**
     * places represent how many digits after the decimal point.
     * ie. 0 places means generate an integer number
     * 1 place means generate a number like 10.1
     * @param max1
     * @param places1
     * @param max2
     * @param places2
     */
    public DecimalMultiplication(int max1, int places1, int max2, int places2) {
        this.max1 = max1;
        this.places1 = places1;
        this.max2 = max2;
        this.places2 = places2;

        double ten1 = Math.pow(10, places1);
        double ten2 = Math.pow(10, places2);

        Random r = new Random();
        double a = r.nextInt((int)Math.round(max1 * ten1)) / ten1;
        double b = r.nextInt((int)Math.round(max2 * ten2)) / ten2;

        ans = Math.round(a*b*ten1)/ten1;
        prompt = a + " x " + b + " = ?";
    }

    public boolean checkAnswer(String text) {
        try {
            return Double.parseDouble(text) == ans;
        } catch (Exception e) {
            return false;
        }
    }

    public Problem next() {
        return new DecimalMultiplication(max1, places1, max2, places2);
    }
}
