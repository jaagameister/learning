package in.jaaga.learning.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import in.jaaga.learning.ChatItem;
import in.jaaga.learning.DB;
import in.jaaga.learning.Learning;
import in.jaaga.learning.Problem;
import in.jaaga.learning.R;
import in.jaaga.learning.Skill;
import in.jaaga.learning.problems.SimpleProblem;

// TODO: rename to Vocab
public class AndroidImages implements Skill {
//    static ArrayList<String> imagesList = DB.getImagesList();
//    int resourceId = S.getResources().getIdentifier(imageName, "drawable", S.getActivity().getPackageName());

    int imageId;

    String[] categories;
    int cIndex = 0;

    String[] nouns;
    int nIndex =0;

    public AndroidImages() {
        categories = S.getResources().getStringArray(R.array.vocab);
    }

    public void loadNouns() {
        int nounListId = S.getResources().getIdentifier(categories[cIndex], "array", S.getActivity().getPackageName());
        nouns = S.getResources().getStringArray(nounListId);
    }

    public String getPrompt() {
        return "vocab: "+nouns[nIndex];
    }

    public ChatItem getPromptChatItem() {
        if (nouns == null)
            loadNouns();
        if (nIndex >= nouns.length) {
            cIndex++;
            nouns = null;
            nIndex = 0;
        }

        imageId = S.getResources().getIdentifier(nouns[nIndex], "drawable", S.getActivity().getPackageName());


        ChatItem p = new ChatItem(choicePrompt(), imageId, Learning.TEXT_RESPONSE);
        return p;
    }

    private String choicePrompt() {
        return "("+nouns[nIndex]+" | "+nouns[new Random().nextInt(nouns.length)]+")";
    }

    public void processResponse(String response) {
        if (nouns[nIndex].equals(response)) {
            nIndex++;
            // TODO response
        }
    }

    public void save(HashMap<String, String> session) {
        session.put("category", Integer.valueOf(cIndex).toString());
        session.put("noun", Integer.valueOf(nIndex).toString());
    }

    public void restore(HashMap<String, String> session) {
        if (session.get("category") != null) {
            cIndex = Integer.valueOf(session.get("category")).intValue();
            nIndex = Integer.valueOf(session.get("noun")).intValue();
        }
        loadNouns();
    }

    public String getTitle() {
        return "Images Guessing";
    }

    public String getHint() {
        return "come on";
    }

    public int getPoints() {
        return 100;
    }
}

