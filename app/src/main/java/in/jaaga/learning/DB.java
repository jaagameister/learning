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
    public int getUidFromUserName(String name){return -2;}
    public boolean skillAttemptedBefore(String name,String skill){return false;}
    public void insertSkillAttemptedInDatabase(String name, String skill,int points){}
    public void updatePointsScored(String name, String skill, int points){}
    public int getPointsFromDatabase(String name,String skill){return 0;}
    public String getData(String name,String skill){return "nothing";}
}