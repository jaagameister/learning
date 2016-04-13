package in.jaaga.learning.problems;

import java.util.*;

import in.jaaga.learning.Problem;

/**
 * Created by Dheeraj on 8/2/16.
 */
public class Fraction extends SimpleProblem {
    int max;

    public Fraction(int max) {
        int a, b;
        this.max = max;
        a = new Random().nextInt(max);
        b = new Random().nextInt(max) + 1;
        prompt = a + " ÷ " + b + " = ?";

        int hcf=-1;
        int min = Math.min(a,b);

        for(int i=min; i >= 1; i--) {
            if(a%i == 0 && b%i == 0) {
                hcf = i;
                break;
            }
        }
        if(b%hcf == 0) {
            answer = a/hcf + "";
        }
        if(a > b) {
            int d = a/b;
            int r = a%b;
            answer = d +" " + r + "/" + b;
        }
        else {
            answer  = a +"/" + b;
        }
    }

    public Problem next() {
        return new Fraction(max);
    }

    public Fraction(int max1, int max2) {
        this(max1);
    }

    public String getTitle() {
        return "Fraction to simplest form " + max;
    }


}
