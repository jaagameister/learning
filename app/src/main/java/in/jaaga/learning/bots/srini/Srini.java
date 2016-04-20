package in.jaaga.learning.bots.srini;

import java.util.Random;

import in.jaaga.learning.R;
import in.jaaga.learning.api.Bot;
import in.jaaga.learning.api.ChatItem;
import in.jaaga.learning.bots.srini.problems.Addition;
import in.jaaga.learning.bots.srini.problems.Subtraction;

/**
 * Created by freeman on 19/4/16.
 */
public class Srini extends Bot {
    Mission mission = new Mission(sender);

    public void init() {
        mission.add(new ProblemSkill(this, new Addition(10), 5, 100));
        mission.add(new ProblemSkill(this, new Subtraction(10), 5, 100));
    }

    public void onStart() {
        super.onStart();
        init();
        String[] hello = getResources().getStringArray(R.array.hello);
        sender.send(new ChatItem(hello[new Random().nextInt(hello.length)]));
        sender.send(mission.getPrompt());
    }

    public void onMessageReceived(String text) {
        mission.processResponse(text);
        sender.send(mission.getPrompt());
    }
}
