package in.jaaga.learning.problems;

import java.util.*;
import java.math.*;

import in.jaaga.learning.ChatItem;
import in.jaaga.learning.Learning;
import in.jaaga.learning.Problem;

public class DecimalAddition implements Problem {

	int max, min, digitsNumber;
	BigDecimal a, b, c;
	ArrayList<String> answerList = new ArrayList<String>();

    public DecimalAddition (int max, int min, int digitsNumber) {
		this.max = max;
		this.min = min;
		this.digitsNumber = digitsNumber;

		a = generateRandomBigDecimal();
		a = a.setScale(digitsNumber, RoundingMode.CEILING);
		b = generateRandomBigDecimal();
		b = b.setScale(digitsNumber, RoundingMode.CEILING);
		c = new BigDecimal(0).add(a).add(b);
		c = c.setScale(digitsNumber, RoundingMode.CEILING);

		String loopAnswer = String.valueOf(c);

		if (loopAnswer.matches("\\d+.\\d+0+|\\d+.0+")){

			int len = loopAnswer.length();
			int j = 0;
			while (loopAnswer.substring(len - j - 1,len -j).equals("0")){
				j++;
			}

			if (loopAnswer.matches("\\d+.0+")) {
				answerList.add(loopAnswer.substring(0, 1));
			} else {
				j++;
			}

			for (int i = 0; i < j; i++){
				answerList.add(loopAnswer.substring(0, loopAnswer.length() - i));
			}
		} else {
			answerList.add(String.valueOf(c));
		}
    }

	public String getPrompt() {
		return a + " + " + b + " = ?";
	}

	public ChatItem getPromptChatItem() {
		return new ChatItem(getPrompt(), Learning.NUMBER_RESPONSE);
	}

	public boolean checkAnswer(String ans) {
		return answerList.contains(ans);
	}

	BigDecimal generateRandomBigDecimal(){
		int range = (max - min);
		BigDecimal randomNumber = new BigDecimal((Math.random() * range) + min);
		return randomNumber;
	}

	public String getHint() {
		String answer = "";
		if (answerList.size() == 1) {
			answer = "The answer is: " + answerList.get(0);
		} else {
			answer = "Possible answers:\n";
			for (String s : answerList) {
				answer = answer.concat(s).concat("\n");
			}
			answer = answer.substring(0, answer.length()-2);
		}
		return answer;
	}

	public Problem next() {
		return new DecimalAddition(max, min, digitsNumber);
	}

    public String getTitle() {
    	return "Decimal addition with variables upto " + max;
    }
}
