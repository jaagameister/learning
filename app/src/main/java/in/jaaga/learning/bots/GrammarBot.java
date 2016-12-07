package in.jaaga.learning.bots;

import java.util.Random;

import in.jaaga.learning.api.*;
import in.jaaga.learning.bots.skillbot.MathBot;

/**
 * Created by freeman on 28/4/16.
 */

public class GrammarBot extends Bot {
    int count = 1;
    String answer;

    @Override
    public void onStart() {
        super.onStart();
        sender.send(new ChatItem("what is the past tense form of the verbs"));
        showPrompt();
    }

    public void showPrompt() {
        // get sentence
        int sId = getResources().getIdentifier("verb" + count, "string", getPackageName());
        if (sId == 0) {
            count = 1;
            sId = getResources().getIdentifier("verb" + count, "string", getPackageName());
        }
        String sentence = getResources().getString(sId);

        // get options
        int optionId = getResources().getIdentifier("verb" + count, "array", getPackageName());
        String[] options = getResources().getStringArray(optionId);
        answer = options[0];
        sender.send(new ChatItem(sentence, randomize(options)));
    }

    @Override
    public void onMessageReceived(String text) {
        if (answer.equalsIgnoreCase(text)) {
            sender.send(new ChatItem("Awesome", ChatItem.NO_RESPONSE));
            count++;
            showPrompt();
            return;
        }
        sender.send(new ChatItem("Nope. Try again", ChatItem.NO_RESPONSE));
        showPrompt();
    }

    private String[] randomize(String[] options) {
        Random r = new Random();
        for (int i = 0; i < options.length; i++) {
            int index = r.nextInt(options.length);
            String tmp = options[index];
            options[index] = options[i];
            options[i] = tmp;
        }
        return options;
    }
}
