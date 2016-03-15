/*package in.jaaga.learning.android.problems;

import java.util.Random;

import in.jaaga.learning.ChatItem;
import in.jaaga.learning.Learning;
import in.jaaga.learning.Problem;
import in.jaaga.learning.R;
import in.jaaga.learning.android.S;
import in.jaaga.learning.problems.SimpleProblem;

public class VegitableImages extends SimpleProblem {
    String[] NAMES = {
            S.getResources().getString(R.string.beetroot),
            S.getResources().getString(R.string.lettuce),
            S.getResources().getString(R.string.pumpkin),
            S.getResources().getString(R.string.cabbage),
            S.getResources().getString(R.string.capsicum),
            S.getResources().getString(R.string.carrot),
            S.getResources().getString(R.string.potato),
            S.getResources().getString(R.string.luffa),
            S.getResources().getString(R.string.raddish),
            S.getResources().getString(R.string.spinach),
            S.getResources().getString(R.string.tomato),
            S.getResources().getString(R.string.turnip),
            S.getResources().getString(R.string.cauliflower),
            S.getResources().getString(R.string.cabbage),
            S.getResources().getString(R.string.corinder),
            S.getResources().getString(R.string.corn),
            S.getResources().getString(R.string.chilli),
            S.getResources().getString(R.string.cucumber),
            S.getResources().getString(R.string.brinjal),
            S.getResources().getString(R.string.garlic),
            S.getResources().getString(R.string.ginger),
            S.getResources().getString(R.string.jackfruit),
            S.getResources().getString(R.string.mushroom),
            S.getResources().getString(R.string.onion),
            S.getResources().getString(R.string.pea),
            S.getResources().getString(R.string.mint)
    };
    int current = 0;

    public VegitableImages() {
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
        return "vegitable";
    }

    public String getHint() {
        return answer;
    }

    public int getNumPrompts() {
        return NAMES.length;
    }
}
*/