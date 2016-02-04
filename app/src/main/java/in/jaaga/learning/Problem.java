package in.jaaga.learning;

import java.lang.reflect.* ;

public abstract class Problem {
    String prompt;
    String answer;

    public String getPrompt() {
    	return prompt;
    }

    public boolean checkAnswer(String ans) {
    	return answer.equals(ans);
    }

    public void setPrompt(String prompt) {
    	this.prompt = prompt;
    }

    public void setAnswer(String answer) {
    	this.answer = answer;
    }

    public String getHint() {
        return answer;
    }

    public abstract Problem next();

    public abstract String getTitle();
}
