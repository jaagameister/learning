package in.jaaga.learning.problems;

import java.util.*;
import in.jaaga.learning.Problem;

public class PowerFunction extends SimpleProblem {
    int max;

    public PowerFunction(int max) {
        this.max = Math.abs(max);;
        int x, exp;
        int y = 1;
		exp = new Random().nextInt(2)+2;
		x = new Random().nextInt(max+1);

        if (new Random().nextInt(2) == 1){
            x = x*(-1);
        }

        for (int i = 1; i <= exp; i++) {
            y *= x;
        }

        prompt = "y = x ^ "+exp +"\nIf x = "+ x 
        +" what's the value of y?";
        answer = Integer.toString(y);
    }

    public Problem next() {
        return new PowerFunction(max);
    }

    public String getTitle() {
        int m = Math.abs(max);
        if (max > 0)
            return "Power Function to "+max;
        else
            return "Power Function to "+max+"with negative numbers";
    }
}

