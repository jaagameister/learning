package in.jaaga.learning.android.problems;

import in.jaaga.learning.ChatItem;
import in.jaaga.learning.Problem;
import in.jaaga.learning.R;
import in.jaaga.learning.android.S;

/**
 * Created by freeman on 1/3/16.
 */
public class Story implements Problem {
    String[] pages;
    int page = 0;

    public Story(String name) {
        int pagesResourceId = S.getActivity().getResources().getIdentifier(name, "array", S.getActivity().getPackageName());
        if (pagesResourceId > 0)
            pages = S.getActivity().getResources().getStringArray(pagesResourceId);
    }

    public String getPrompt() {
        return pages[page++];
    };

    public ChatItem getPromptChatItem() {
        return new ChatItem(getPrompt());
    }

    public boolean checkAnswer(String answer) {
        return true;
    }
    public String getHint() {
        return S.getResources().getString(R.string.story1_hint);
    }
    public String getTitle() {
        return S.getResources().getString(R.string.story1_title);
    }
    public Problem next() {
        return this;
    }

    public int getNumPrompts() {
        if (pages == null)
            return 0;
        return pages.length;
    }
}
