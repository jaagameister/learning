package in.jaaga.learning.android.problems;

import java.util.Random;

import in.jaaga.learning.ChatItem;
import in.jaaga.learning.Learning;
import in.jaaga.learning.Problem;
import in.jaaga.learning.R;
import in.jaaga.learning.android.S;
import in.jaaga.learning.problems.SimpleProblem;

/**
 * Created by dolly on 8/3/16.
 */
public class AnimalImages extends SimpleProblem {
    String[] NAMES = {
            S.getResources().getString(R.string.ant),
            S.getResources().getString(R.string.bear),
            S.getResources().getString(R.string.buffalo),
            S.getResources().getString(R.string.butterfly),
            S.getResources().getString(R.string.camel),
            S.getResources().getString(R.string.cat),
            S.getResources().getString(R.string.cow),
            S.getResources().getString(R.string.crocodile),
            S.getResources().getString(R.string.deer),
            S.getResources().getString(R.string.squirrel),
            S.getResources().getString(R.string.pig),
            S.getResources().getString(R.string.rabbit),
            S.getResources().getString(R.string.sheep),
            S.getResources().getString(R.string.spider),
            S.getResources().getString(R.string.tiger),
            S.getResources().getString(R.string.turtle),
            S.getResources().getString(R.string.snake),
            S.getResources().getString(R.string.wolf),
            S.getResources().getString(R.string.dog),
            S.getResources().getString(R.string.donkey),
            S.getResources().getString(R.string.elephant),
            S.getResources().getString(R.string.frog),
            S.getResources().getString(R.string.fox),
            S.getResources().getString(R.string.goat),
            S.getResources().getString(R.string.horse),
            S.getResources().getString(R.string.lion),
            S.getResources().getString(R.string.monkey),
            S.getResources().getString(R.string.mouse)
    };
    int current = 0;

    public AnimalImages() {
    }

    public ChatItem getPromptChatItem() {
        current = new Random().nextInt(NAMES.length);
        answer = NAMES[current];
        int imageId = S.getResources().getIdentifier(NAMES[current], "drawable", S.getActivity().getPackageName());
        return new ChatItem("What is this?", imageId, Learning.TEXT_RESPONSE);
    }

    public boolean checkAnswer(String answer) {
        return this.answer == answer;
    }

    public Problem next() {
        return this;
    }

    public String getTitle() {
        return "birds";
    }

    public String getHint() {
        return answer;
    }

    public int getNumPrompts() {
        return NAMES.length;
    }
}
