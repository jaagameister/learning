
public abstract class Problem {
    String prompt;
    String answer;
    int max = -1;
    
    public Problem() {}

    public Problem(int max) {
        this.max = max;
    }

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

    public abstract Problem next();
}
