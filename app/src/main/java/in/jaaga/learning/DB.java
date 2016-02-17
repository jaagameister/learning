package in.jaaga.learning;
import java.util.*;

/**
 * Created by root on 14/2/16.
 */
public class DB {
    HashMap<String, String> db = new HashMap();
    static ArrayList<String> imagesList = new ArrayList<>();


    public void addName(String name) {
        db.put(name, "password");
    }

    public boolean containsName(String name) {
        return db.containsKey(name);
    }

    public static ArrayList getImagesList(){
        imagesList.add("cat");
        imagesList.add("dog");
        imagesList.add("tree");
        imagesList.add("ball");
        imagesList.add("car");

        return imagesList;
    }
}