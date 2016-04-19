package in.jaaga.learning.bots.srini;

import in.jaaga.learning.api.Bot;
import in.jaaga.learning.api.ChatItem;
import in.jaaga.learning.bots.srini.problems.Addition;

/**
 * Created by freeman on 19/4/16.
 */
public class Srini extends Bot {
    Mission mission = new Mission(sender);

    public void init() {
        mission.add(new ProblemSkill(sender, new Addition(10), 50, 100));
    }

    public void onStart() {
        super.onStart();
        init();
        sender.send(new ChatItem("hello"));
        sender.send(mission.getPrompt());
    }

    public void onMessageReceived(String text) {
        mission.processResponse(text);
        sender.send(mission.getPrompt());
    }
}
