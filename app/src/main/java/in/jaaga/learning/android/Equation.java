package in.jaaga.learning.android;

import java.util.Random;

import in.jaaga.learning.Problem;
import in.jaaga.learning.problems.SimpleProblem;

/**
 * Created by freeman on 11/4/16.
 */
public class Equation extends SimpleProblem {

    public Equation() {
        Random r = new Random();
        int coefficient = r.nextInt(10) + 1;
        int constant = r.nextInt(10) + 1;
        int x = r.nextInt(10);
        int rightSide = coefficient * x + constant;

        // 3(x) + 4 = 19
        // x = ?
        prompt = coefficient+"(x) + "+constant+" = "+rightSide+"\nx = ?";
        answer = Integer.toString(x);
    }

    public Problem next() {
        return new Equation();
    }

    public String getTitle() {
        return "simple equations of the form 3(x) + 6 = 18. Solve for X";
    }
}
