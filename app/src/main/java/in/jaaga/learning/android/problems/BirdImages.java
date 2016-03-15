/*package in.jaaga.learning.android.problems;

import java.util.Random;

import in.jaaga.learning.ChatItem;
import in.jaaga.learning.Learning;
import in.jaaga.learning.Problem;
import in.jaaga.learning.R;
import in.jaaga.learning.android.S;
import in.jaaga.learning.problems.SimpleProblem;

public class BirdImages extends SimpleProblem {
    String[] NAMES = {
            S.getResources().getString(R.string.cock),
            S.getResources().getString(R.string.cuckoo),
            S.getResources().getString(R.string.crane),
            S.getResources().getString(R.string.crow),
            S.getResources().getString(R.string.dove),
            S.getResources().getString(R.string.eagle),
            S.getResources().getString(R.string.hen),
            S.getResources().getString(R.string.kingfisher),
            S.getResources().getString(R.string.kite),
            S.getResources().getString(R.string.mynah),
            S.getResources().getString(R.string.ostrich),
            S.getResources().getString(R.string.owl),
            S.getResources().getString(R.string.peacock),
            S.getResources().getString(R.string.pigeon),
            S.getResources().getString(R.string.skylark),
            S.getResources().getString(R.string.sparrow),
            S.getResources().getString(R.string.swan),
            S.getResources().getString(R.string.turkey),
            S.getResources().getString(R.string.woodpecker)
    };
    int current = 0;

    public BirdImages() {
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
*/