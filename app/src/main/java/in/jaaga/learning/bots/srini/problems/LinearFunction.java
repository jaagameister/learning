package in.jaaga.learning.bots.srini.problems;

import java.util.Random;

import in.jaaga.learning.bots.srini.Problem;

/**
 * Created by harish on 13/4/16.
 */
public class LinearFunction extends SimpleProblem {

    public LinearFunction() {
        int a = new Random().nextInt(100);
        int b = new Random().nextInt(100);
        int x = new Random().nextInt(10);
        int ans =  a*x + b;

        prompt = "f(x) = " + a + "x" +  " + " + b + "\n" + "f(" + x + ") = ? ";
        answer = String.valueOf(ans);
    }

    @Override
    public String getTitle() {
        return "Linear function";
    }

    @Override
    public Problem next() {
        return new LinearFunction();
    }
}
