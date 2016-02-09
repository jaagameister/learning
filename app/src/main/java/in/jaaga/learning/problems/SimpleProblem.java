package in.jaaga.learning.problems;

import in.jaaga.learning.Problem;

/**
 * Created by freeman on 6/2/16.
 */
public abstract class SimpleProblem implements Problem {
    String prompt, answer;

    public String getPrompt() {
        return prompt;
    }

    public boolean checkAnswer(String answer) {
        return this.answer.equals(answer);
    }

    public String getHint() {
        return answer;
    }
}
