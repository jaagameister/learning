package in.jaaga.learning.problems;
import java.util.*;

import in.jaaga.learning.Problem;

/**
 * Created by root on 8/2/16.
 */
public class Percent extends SimpleProblem {
        int max;

        public Percent(int max) {
            int a, b;
            this.max = max;
            a = new Random().nextInt(max);
            b = new Random().nextInt(max);

            prompt = a + " % of " + b + " = ?";
            float z = ((float) a/100)*b;
            String ans = String.valueOf(z);
            answer =   String.valueOf(ans);
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
