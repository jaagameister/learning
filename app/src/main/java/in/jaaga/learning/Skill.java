package in.jaaga.learning;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created by freeman on 8/3/16.
 */
public interface Skill {
    public String getPrompt();
    public ChatItem getPromptChatItem();
    public void processResponse(String response);
    public String getHint();
    public String getTitle();
    public int getPoints();
    public void save(HashMap<String, String> session);
    public void restore(HashMap<String, String> session);
}
