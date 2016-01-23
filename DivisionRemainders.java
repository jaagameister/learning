import java.util.*;

public class DivisionRemainders extends Problem {

    public DivisionRemainders(int dividendMax, int divisorMax) {
        init(dividendMax, divisorMax);
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

	public String getTitle() {
        return "Division with dividend to " + max1 + 
                " and divsor to " + max2 +
				" and remainders";
	}
}