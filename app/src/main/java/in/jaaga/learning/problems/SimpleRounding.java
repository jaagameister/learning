package in.jaaga.learning.problems;

import java.util.Random;

import in.jaaga.learning.Problem;

/**
 * Created by harish on 13/4/16.
 */
public class SimpleRounding extends SimpleProblem{

    public SimpleRounding(){
        Random random = new Random();
        Double initial_double = random.nextDouble()*random.nextInt(100);
        Double prob = Math.round(initial_double*10.0)/10.0;
        Long ans = Math.round(prob);

        prompt = prob.toString();
        answer = ans.toString();
    }
    public String getTitle() {
        return "Simple Rounding. For example, 25.6 = 26";
    }

    @Override
    public Problem next() {
        return new SimpleRounding();
    }
}
