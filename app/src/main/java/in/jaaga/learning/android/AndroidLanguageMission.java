package in.jaaga.learning.android;

import java.util.ArrayList;

import in.jaaga.learning.Skill;
import in.jaaga.learning.Mission;

/**
 * Created by admin on 24/02/2016.
 */
public class AndroidLanguageMission implements Mission {
    public ArrayList<Skill> getList() {
        ArrayList<Skill> mission = new ArrayList<Skill>();
        mission.add(new Skill(new AndroidImages(), 500, 1000));
        return mission;
    }

    public String getTitle() {
        return "language skills";
    }
}