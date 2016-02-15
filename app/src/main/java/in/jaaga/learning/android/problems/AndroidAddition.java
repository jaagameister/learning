package in.jaaga.learning.android.problems;

import java.util.*;

import in.jaaga.learning.Problem;
import in.jaaga.learning.problems.Addition;
import in.jaaga.learning.problems.SimpleProblem;
import in.jaaga.learning.R;
import in.jaaga.learning.android.S;

public class AndroidAddition extends Addition {
    int max;

    public AndroidAddition(int max) {
        super(max);
    }

    public Problem next() {
        return new AndroidAddition(max);
    }

    public String getTitle() {
        int m = Math.abs(max);
        if (max > 0)
            return S.getResources().getString(R.string.addition_title, max);
        else
            return S.getResources().getString(R.string.addition_negative_title, max);
    }
}

