package in.jaaga.learning.bots.srini.problems;

import java.util.HashMap;

import in.jaaga.learning.api.ChatItem;
import in.jaaga.learning.bots.srini.Problem;

/**
 * Created by freeman on 6/2/16.
 */
public abstract class SimpleProblem implements Problem {
    protected String prompt, answer;

    public String getPrompt() {
        return prompt;
    }

    public ChatItem getPromptChatItem() {
        return new ChatItem(getPrompt(), ChatItem.NUMBER_RESPONSE);
    }

    public boolean checkAnswer(String answer) {
        return this.answer.equalsIgnoreCase(answer);
    }

    public String getHint() {
        return answer;
    }

    public void save(HashMap<String, String> session) {
//        session.put("answer", answer);
    }

    public void restore(HashMap<String, String> session) {
//        if (session.get("answer") != null)
//            answer = (String)session.get("answer");
    }
}
