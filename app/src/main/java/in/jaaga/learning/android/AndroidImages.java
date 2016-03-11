package in.jaaga.learning.android;

import java.util.ArrayList;
import java.util.Random;

import in.jaaga.learning.ChatItem;
import in.jaaga.learning.DB;
import in.jaaga.learning.Learning;
import in.jaaga.learning.Problem;
import in.jaaga.learning.R;
import in.jaaga.learning.problems.SimpleProblem;

public class AndroidImages extends SimpleProblem {
//    static ArrayList<String> imagesList = DB.getImagesList();
//    int resourceId = S.getResources().getIdentifier(imageName, "drawable", S.getActivity().getPackageName());



    int imageId;

    public AndroidImages() {
    }

    public ChatItem getPromptChatItem() {
        int[] IMAGE_RESOURCES = {R.drawable.ball, R.drawable.car, R.drawable.dog, R.drawable.tree};
        String[] NAMES = {
                S.getResources().getString(R.string.ball),
                S.getResources().getString(R.string.car),
                S.getResources().getString(R.string.dog),
                S.getResources().getString(R.string.tree)
        };
        Random r = new Random();
        int rand = r.nextInt(IMAGE_RESOURCES.length);
        answer = NAMES[rand];
        imageId = IMAGE_RESOURCES[rand];
        return new ChatItem("What is this?", imageId, Learning.TEXT_RESPONSE);
    }

    public Problem next() {
        return new AndroidImages();
    }

    public String getTitle() {
        return "Images Guessing";
    }
}

