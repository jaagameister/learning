package in.jaaga.learning;

import java.lang.reflect.* ;
import java.util.HashMap;
import java.util.Properties;

public interface Problem {
    public String getPrompt();
    public ChatItem getPromptChatItem();
    public boolean checkAnswer(String answer);
    public String getHint();
    public String getTitle();
    public Problem next();
    public void save(HashMap<String, String> session);
    public void restore(HashMap<String, String> session);
}
