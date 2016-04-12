package in.jaaga.learning.problems;

import android.util.Log;

import java.util.Random;

import in.jaaga.learning.Problem;

/**
 * Created by harish on 12/4/16.
 */
public class RoundingProblems extends SimpleProblem implements Problem {
    static final String LOG_TAG = "Rounding Problems";
    public RoundingProblems() {
        Double prob = new Random().nextDouble();
        //Log.d(LOG_TAG,prob.toString());
        // rounding logic
        Double ans = Math.round(prob*1000.0)/1000.0;
        prompt = prob.toString();
        answer = ans.toString();
    }

    @Override
    public String getTitle() {
        return "Rounding problems upto 3 decimal places./n For example, 3.1426898 = 3.143";
    }

    @Override
    public Problem next() {
        return new RoundingProblems();
    }
}
