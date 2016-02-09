package in.jaaga.learning;

import java.lang.reflect.* ;

public interface Problem {
    public String getPrompt();
    public boolean checkAnswer(String answer);
    public String getHint();
    public String getTitle();
    public Problem next();
}
