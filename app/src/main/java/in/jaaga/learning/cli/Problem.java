package in.jaaga.learning.cli;

import java.lang.reflect.* ;

public abstract class Problem {
    String prompt;
    String answer;
    int max1 = -1;
    int max2 = -1;
    
    public Problem() {}

    public Problem(int max) {
        init(max);
    }

    public Problem(int max1, int max2) {
        init(max1, max2);
    }

    public void init(int max) {
        max1 = max;
    }

    public void init(int max1, int max2) {
        this.max1 = max1;
        this.max2 = max2;
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

    public String getHint() {
        return answer;
    }

    public Problem next() {
        try {
            Constructor<? extends Problem> constructor = 
                getClass().getDeclaredConstructor(Integer.TYPE, Integer.TYPE) ;
            return constructor.newInstance(max1, max2);
        } catch( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract String getTitle();
}
