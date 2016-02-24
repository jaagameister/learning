package in.jaaga.learning.problems;

import java.util.*;

import in.jaaga.learning.ChatItem;
import in.jaaga.learning.Learning;
import in.jaaga.learning.Problem;

public class DivisionRemainders implements Problem {
	int dividendMax, divisorMax;
	int a, b, r;

    public DivisionRemainders(int dividendMax, int divisorMax) {
		this.dividendMax = dividendMax;
		this.divisorMax = divisorMax;
     	a = new Random().nextInt(dividendMax);
		b = new Random().nextInt(divisorMax) + 1;
		r = a % b;
    }

	public String getPrompt() {
		return a + " ÷ " + b + " = ?";
	}

	public ChatItem getPromptChatItem() {
		return new ChatItem(getPrompt(), Learning.NUMBER_RESPONSE);
	}

	public boolean checkAnswer(String answer) {
		if (Integer.toString(a / b).equals(answer) && r == 0)
			return true;
		if (answer.equals(Integer.toString(a / b) + " " + r))
			return true;
		return false;
	}

	public String getHint() {
		return Integer.toString(a / b) + " " + r;
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
