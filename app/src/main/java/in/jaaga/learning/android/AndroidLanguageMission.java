package in.jaaga.learning.android;

import java.util.ArrayList;

import in.jaaga.learning.Skill;
import in.jaaga.learning.Mission;
import in.jaaga.learning.android.problems.Story;

/**
 * Created by admin on 24/02/2016.
 */
public class AndroidLanguageMission implements Mission {
    public ArrayList<Skill> getList() {
        ArrayList<Skill> mission = new ArrayList<Skill>();
        Story story1 = new Story("story1");
        mission.add(new Skill(story1, story1.getNumPrompts(), 1000));  // TODO: ask the problem if its done.
        Story story2 = new Story("story2");
        mission.add(new Skill(story2, story2.getNumPrompts(), 1000));  // TODO: ask the problem if its done.
        mission.add(new Skill(new AndroidImages(), 200, 1000));
        return mission;
    }

    public String getTitle() {
        return "language skills";
    }
}