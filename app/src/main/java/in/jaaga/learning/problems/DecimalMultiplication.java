package in.jaaga.learning.problems;

import java.util.*;
import java.math.*;

import in.jaaga.learning.Problem;

public class DecimalMultiplication implements Problem {

    int max, min, digitsNumber;
    BigDecimal a, b, c;
    ArrayList<String> answerList = new ArrayList<String>();

    public DecimalMultiplication(int max, int min, int digitsNumber) {
        this.max = max;
        this.min = min;
        this.digitsNumber = digitsNumber;

        a = generateRandomBigDecimal(); // where does this method live?
        a = a.setScale(digitsNumber, RoundingMode.CEILING); // why not RoundingMode.HALF_UP?
        b = generateRandomBigDecimal();
        b = b.setScale(digitsNumber, RoundingMode.CEILING);
        c = new BigDecimal(1).multiply(a).multiply(b);
        c = c.setScale(digitsNumber, RoundingMode.CEILING);

        String loopAnswer = String.valueOf(c);

        if (loopAnswer.matches("\\d+.\\d+0+|\\d+.0+")) {
            int len = loopAnswer.length();
            int j = 0;
            while (loopAnswer.substring(len - j - 1, len - j).equals("0")) {
                j++;
            }
            if (loopAnswer.matches("\\d+.0+")) {
                answerList.add(loopAnswer.substring(0, 1)); // copied from DecAdd; doesn't this add "5" as an answer for 52.000?
            } else {
                j++; // copied from DecAdd; when (loopAnswer.matches("\\d+.0+")), 
                // doesn't j need to be incremented once more, not once less, than otherwise?
            }
            for (int i = 0; i < j; i++) {
                answerList.add(loopAnswer.substring(0, len - i));                
            }
        } else {
            answerList.add(String.valueOf(c));
        }
    }

    public String getPrompt() {
        return a + " X " + b + " = ?";
    }

    public boolean checkAnswer(String ans) {
        return answerList.contains(ans);
    }

    BigDecimal generateRandomBigDecimal() { // maybe this should be in Problem or some other construct like "ComplexProblem"?
        int range = (max - min); // shouldn't max & min be listed as parameters?
        BigDecimal randomNumber = new BigDecimal(Math.random() * range + min);
        return randomNumber;
    }

    public String getHint() {
        String answer = "";
        if (answerList.size() == 1) {
            answer = "The answer is: " + answerList.get(0);
        } else {
            answer = "Possible answers:\n";
            for (String s : answerList) {
                answer.concat(s).concat("\n");
            }
            answer = answer.substring(0, answer.length()-2); // not sure what this do
        }
        return answer;
    }

    public Problem next() {
        return new DecimalMultiplication(max, min, digitsNumber);
    }

    public String getTitle() {
        return "Decimal multiplication with variables up to " + max;
    }
}
