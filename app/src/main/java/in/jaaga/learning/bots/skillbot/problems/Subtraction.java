package in.jaaga.learning.bots.skillbot.problems;
import java.util.*;

import in.jaaga.learning.bots.skillbot.Problem;


public class Subtraction extends SimpleProblem {
    int max;

    public Subtraction(int max) {
        int a, b;
    	this.max = max;

        if (max > 0) {
        	a = new Random().nextInt(max);
    		b = new Random().nextInt(a + 1);
        } else {  // max is negative
            a = new Random().nextInt(max * -2) + max;
            b = new Random().nextInt(max * -2) + max;
        }       
        prompt = a + " - " + b + " = ?";
        answer = Integer.toString(a - b);
    }

    public Problem next(){
        return new Subtraction(max);
    }

    public Subtraction(int max1, int max2) {
        this(max1);
    }

    public String getTitle() {
        int m = Math.abs(max);
        String title = "Subtraction to " + max;
        if (max > 0)
            return title;
        return title + " with negative numbers";
    }


}
