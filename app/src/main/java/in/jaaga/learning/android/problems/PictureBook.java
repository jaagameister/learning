package in.jaaga.learning.android.problems;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import in.jaaga.learning.ChatItem;
import in.jaaga.learning.Learning;
import in.jaaga.learning.Problem;
import in.jaaga.learning.R;
import in.jaaga.learning.android.S;

/**
 * Created by freeman on 2/3/16.
 */
public class PictureBook implements Problem {
    ArrayList<Integer> book = new ArrayList<Integer>();
    int page = 0;

    public PictureBook(String name) {
        int count = 1;
        int resourceId = S.getResources().getIdentifier(name+count, "drawable", S.getActivity().getPackageName());
        Log.d("PictureBook", "resourceId " + resourceId);
        System.out.println("PictureBook + resourceId " + resourceId);
        while (resourceId != -1) {
            book.add(new Integer(resourceId));
            String pageName = name + (++count);
            System.out.println("pageName: "+pageName);
            resourceId = S.getResources().getIdentifier(pageName, "drawable", S.getActivity().getPackageName());
            System.out.println("resourceId " + resourceId);
        }
    }

    public String getPrompt() {
//        return pages[page++];
        return "picture book";
    };

    public ChatItem getPromptChatItem() {
        return new ChatItem("", book.get(page++), Learning.TEXT_RESPONSE);
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
        return book.size();
    }
}
