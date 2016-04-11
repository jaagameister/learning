package in.jaaga.learning.android;

import java.util.Random;

import in.jaaga.learning.Problem;
import in.jaaga.learning.problems.SimpleProblem;

/**
 * Created by freeman on 11/4/16.
 */
public class Function extends SimpleProblem {

    public Function() {
        Random r = new Random();
        int coefficient = r.nextInt(10) + 1;
        int constant = r.nextInt(10) + 1;
        int x = r.nextInt(10);
        int rightSide = coefficient * x + constant;

        // f(x) = 3(x) + 4
        // f(5) = ?
        prompt = "f(x) = " + coefficient + "(x) + " + constant + "\nf(" + x + ") = ?";
        answer = Integer.toString(rightSide);
    }

    public Problem next() {
        return new Function();
    }

    public String getTitle() {
        return "simple functions of the form f(x) = 3x + 6 = 18. f(6) = ?";
    }
}
