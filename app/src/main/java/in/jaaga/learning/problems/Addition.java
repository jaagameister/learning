package in.jaaga.learning.problems;
import java.util.*;

import in.jaaga.learning.Problem;

public class Addition extends SimpleProblem {
    int max;

    public Addition(int max) {
        this.max = max;
        int a, b;
        if (max > 0) {
			a = new Random().nextInt(max);
			b = new Random().nextInt(max);
        } else {  // max is negative
            a = new Random().nextInt(max * -2) - (-max);
            b = new Random().nextInt(max * -2) - (-max);
        }
        prompt = a + " + " + b + " = ?";
        answer = Integer.toString(a + b);
    }

    public Problem next() {
        return new Addition(max);
    }

    public String getTitle() {
        int m = Math.abs(max);
        String title = "Addition to " + max;
        if (max > 0)
            return title;
        return title + " with negative numbers";
    }
}

