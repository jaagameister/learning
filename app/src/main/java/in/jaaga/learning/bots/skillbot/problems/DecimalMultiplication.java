package in.jaaga.learning.bots.skillbot.problems;

import java.util.*;
import java.math.*;

import in.jaaga.learning.bots.skillbot.Problem;

public class DecimalMultiplication extends SimpleProblem {

    int max, min;
    BigDecimal a, b, ab;

    public DecimalMultiplication(int max, int min) {
        this.max = max;
        this.min = min;

        a = getRandomDecimal(max, min);
        //b is whole #, to avoid making this too hard
        int rawB = new Random().nextInt(max - min) + min;
        b = new BigDecimal(rawB);
        ab = a.multiply(b);
        prompt = a.setScale(2) + " * " + b.setScale(2) + " = ?";
    }

    BigDecimal getRandomDecimal(int max, int min) {
        /* This method returns a random multiple of either 0.1 or 0.25. */
        boolean tails = new Random().nextBoolean();
        // tails → return multiple of .1; heads → return multiple of .25
        if (tails) {
            int random = new Random().nextInt(max * 10);
            return new BigDecimal(random).divide(BigDecimal.TEN);
        } else {
            int random = new Random().nextInt(max * 4);
            BigDecimal four = new BigDecimal(4);
            return new BigDecimal(random).divide(four);
        }
    }

    public boolean checkAnswer(String ans) { return (Double.parseDouble(ans) == ab.doubleValue()); }

    public String getHint() { return ab.toString(); }

    public Problem next() {
        return new DecimalMultiplication(max, min);
    }

    public String getTitle() {
        return "Decimal multiplication with variables up to " + max;
    }
}
