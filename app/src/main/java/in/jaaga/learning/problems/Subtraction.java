package in.jaaga.learning.problems;
import java.util.*;

import in.jaaga.learning.Problem;


public class Subtraction extends Problem {
    int max;
    public Subtraction(int max) {
    	this.max = max;

        int a,b;
        if (max > 0) {
        	a = new Random().nextInt(max);
    		b = new Random().nextInt(a + 1);
        } else {  // max is negative
            a = new Random().nextInt(max * -2) + max;
            b = new Random().nextInt(max * -2) + max;
        }       
		setPrompt(new String(a + " - " + b + " = ?"));
		setAnswer(new String(Integer.toString(a - b)));
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
