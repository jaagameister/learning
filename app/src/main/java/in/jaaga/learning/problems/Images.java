package in.jaaga.learning.problems;

import java.util.ArrayList;
import java.util.Random;

import in.jaaga.learning.DB;
import in.jaaga.learning.Problem;

public class Images extends SimpleProblem {
    static ArrayList<String> imagesList = DB.getImagesList();

    public Images() {

        Random r = new Random();
        int rand = r.nextInt(imagesList.size());

        prompt = "What is this?*".concat(imagesList.get(rand));
        answer = imagesList.get(rand);

    }


    public Problem next() {
        return new Images();
    }

    public String getTitle() {
        return "Images Guessing";
    }
}

