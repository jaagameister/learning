package in.jaaga.learning.bots.skillbot.problems;

import java.util.*;
import java.math.*;

import in.jaaga.learning.bots.skillbot.Problem;

public class DecimalAddition extends SimpleProblem {
    int max1, max2, places;
    double ans;

    /**
     * places represent how many digits after the decimal point.
     * ie. 0 places means generate an integer number
     * 1 place means generate a number like 10.1
     * @param max1
     * @param max2
     * @param places
     */
    public DecimalAddition(int max1, int max2, int places) {
        this.max1 = max1;
        this.max2 = max2;
        this.places = places;

        double ten = Math.pow(10, places);

        Random r = new Random();
        double a = r.nextInt((int)Math.round(max1 * ten)) / ten;
        double b = r.nextInt((int)Math.round(max2 * ten)) / ten;

        ans = Math.round((a+b)*ten)/ten;
        prompt = a + " + " + b + " = ?";
    }

    public boolean checkAnswer(String text) {
        try {
            return Double.parseDouble(text) == ans;
        } catch (Exception e) {
            return false;
        }
    }

    public Problem next() {
        return new DecimalAddition(max1, max2, places);
    }
}
