package in.jaaga.learning.bots.srini.problems;
import java.util.*;

import in.jaaga.learning.bots.srini.Problem;

/**
 * Created by root on 8/2/16.
 */
public class Percent extends SimpleProblem {
        int max;

        public Percent(int max) {
            int a, b;
            this.max = max;
            a = new Random().nextInt(20)*5;
            b = new Random().nextInt(max);

            prompt = a + "% of " + b + " = ?";
            double z = ((float) a/100)*b;
            z =  Math.round(z*1000.0)/1000.0;
            answer = String.valueOf(z);
        }

        public Problem next(){
            return new Percent(max);
        }

        public Percent(int max1, int max2) {
            this(max1);
        }

        public String getTitle() {
            String title = "Percent upto" + max;
            return title;
        }

    }
