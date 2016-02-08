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
            b = new Random().nextInt(max);

            prompt = a + " / " + b + " = ?";

            int hcf=0;
            int min = Math.min(a,b);

            for(int i=min; i >= 1; i--)
            {
                if(a%i == 0 && b%i == 0)
                {
                    hcf = i;
                    break;
                }
            }

            answer =(a/hcf) + " " + (b/hcf);
        }

    public Problem next(){
        return new Fraction(max);
    }

    public Fraction(int max1, int max2) {
        this(max1);
    }

    public String getTitle() {
        String title = "Fraction to simplest form " + max;
        return title;
    }


}
