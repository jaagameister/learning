import java.util.*;

public class DivisionRemainders extends Problem {
    int dividendMax, divisorMax;

    public DivisionRemainders(int dividendMax, int divisorMax) {
        this.divisorMax = divisorMax;
        this.dividendMax = dividendMax;
     	int a = new Random().nextInt(dividendMax) + 1;
		int b = new Random().nextInt(divisorMax);
		int r = a % b;
		setPrompt(new String(a + " / " + b + " = ?"));
		String ans = new String(Integer.toString(a / b));
		if (r == 0)
			setAnswer(ans);
		else
			setAnswer(ans + " " + r);
		System.out.println("answer: "+answer);
		System.out.println("r: "+r);
    }

	public Problem next() {
		return new DivisionRemainders(dividendMax, divisorMax);
	}

	public String getTitle() {
        return "Division with dividend to " + dividendMax + 
                " and divsor to " + divisorMax +
				" and remainders";
	}
}