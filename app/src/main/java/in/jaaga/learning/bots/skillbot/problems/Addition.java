package in.jaaga.learning.bots.skillbot.problems;

import java.util.*;

import in.jaaga.learning.bots.skillbot.Problem;

public class Addition extends SimpleProblem {
    int max1, max2;

    public Addition(int max) {
        this(max, max);
    }

    public Addition(int max1, int max2) {
        this.max1 = max1;
        this.max2 = max2;

        Random r = new Random();
        int a = r.nextInt(Math.abs(max1));
        if (max1 < 0) a *= -1;
        int b = r.nextInt(Math.abs(max2));
        if (max2 < 0) b *= -1;
        prompt = a + " + " + b + " = ?";
        answer = Integer.toString(a + b);
    }

    public Problem next() {
        return new Addition(max1, max2);
    }
}

