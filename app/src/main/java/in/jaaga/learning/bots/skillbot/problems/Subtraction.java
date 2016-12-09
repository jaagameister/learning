package in.jaaga.learning.bots.skillbot.problems;
import java.util.*;

import in.jaaga.learning.bots.skillbot.Problem;


public class Subtraction extends SimpleProblem {
    int max1, max2;

    public Subtraction(int max) {
        this(max, max);
    }

    public Problem next(){
        return new Subtraction(max1, max2);
    }

    /**
     * assume if max2 > max1 then we want b > a
     **/
    public Subtraction(int max1, int max2) {
        this.max1 = max1;
        this.max2 = max2;

        int a, b;
        Random r = new Random();
        a = r.nextInt(max1);
        b = r.nextInt(max2);
        if (max2 > max1) b += a;
        prompt = a + " - " + b + " = ?";
        answer = Integer.toString(a - b);
    }
}
