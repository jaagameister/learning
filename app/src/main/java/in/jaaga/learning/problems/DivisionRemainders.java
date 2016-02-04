package in.jaaga.learning.problems;

import java.util.*;

import in.jaaga.learning.Problem;

public class DivisionRemainders extends Problem {
	int dividendMax, divisorMax;

    public DivisionRemainders(int dividendMax, int divisorMax) {
		this.dividendMax = dividendMax;
		this.divisorMax = divisorMax;
     	int a = new Random().nextInt(dividendMax);
		int b = new Random().nextInt(divisorMax) + 1;
		int r = a % b;

		setPrompt(new String(a + " / " + b + " = ?"));
		String ans = new String(Integer.toString(a / b));
		if (r == 0)
			setAnswer(ans);
		else
			setAnswer(ans + " " + r);
    }

	public Problem next(){
		return  new Division(dividendMax, divisorMax);
	}

	public String getTitle() {
        return "Division with dividend to " + dividendMax +
                " and divsor to " + divisorMax +
				" and remainders";
	}
}