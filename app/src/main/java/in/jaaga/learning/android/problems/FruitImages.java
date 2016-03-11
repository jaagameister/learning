package in.jaaga.learning.android.problems;

import java.util.Random;

import in.jaaga.learning.ChatItem;
import in.jaaga.learning.Learning;
import in.jaaga.learning.Problem;
import in.jaaga.learning.R;
import in.jaaga.learning.android.S;
import in.jaaga.learning.problems.SimpleProblem;

/**
 * Created by dolly on 9/3/16.
 */
public class FruitImages extends AnimalImages {

    String[] NAMES = {
            S.getResources().getString(R.string.apple),
            S.getResources().getString(R.string.apricot),
            S.getResources().getString(R.string.banana),
            S.getResources().getString(R.string.bilberry),
            S.getResources().getString(R.string.blackberry),
            S.getResources().getString(R.string.blackcurrent),
            S.getResources().getString(R.string.coconut),
            S.getResources().getString(R.string.custurdApple),
            S.getResources().getString(R.string.grapes),
            S.getResources().getString(R.string.guvava),
            S.getResources().getString(R.string.dates),
            S.getResources().getString(R.string.kiwi),
            S.getResources().getString(R.string.lychee),
            S.getResources().getString(R.string.mango),
            S.getResources().getString(R.string.orange),
            S.getResources().getString(R.string.papaya),
            S.getResources().getString(R.string.pear),
            S.getResources().getString(R.string.pineapple),
            S.getResources().getString(R.string.pomegranet),
            S.getResources().getString(R.string.strawberry),
            S.getResources().getString(R.string.watermelon)
    };
    */
    int current = 0;

    public FruitImages() {
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
        return "fruit";
    }

    public String getHint() {
        return answer;
    }

    public int getNumPrompts() {
        return NAMES.length;
    }
}
