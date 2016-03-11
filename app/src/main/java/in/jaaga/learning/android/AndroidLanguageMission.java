package in.jaaga.learning.android;

import java.util.ArrayList;

import in.jaaga.learning.Skill;
import in.jaaga.learning.Mission;
import in.jaaga.learning.android.problems.AnimalImages;
import in.jaaga.learning.android.problems.BirdImages;
import in.jaaga.learning.android.problems.FruitImages;
import in.jaaga.learning.android.problems.PictureBook;
import in.jaaga.learning.android.problems.Sentence;
import in.jaaga.learning.android.problems.Story;
import in.jaaga.learning.android.problems.VegitableImages;

public class AndroidLanguageMission extends Mission {

    public AndroidLanguageMission(LearningContext ctx) {
        super(ctx);

        add(new PictureBook());
        add(new ProblemSkill(ctx, "verb tense sentences", new Sentence(), 10, 200));
/*
        PictureBook book1 = new PictureBook("moon");
        mission.add(new Skill(book1, book1.getNumPrompts(), 1000));

        Story story1 = new Story("story1");
        mission.add(new Skill(story1, story1.getNumPrompts(), 1000));
        Story story2 = new Story("story2");
        mission.add(new Skill(story2, story2.getNumPrompts(), 1000));
        Story story3 = new Story("story3");
        mission.add(new Skill(story3, story3.getNumPrompts(), 1000));
        Story story4 = new Story("story4");
        mission.add(new Skill(story4, story4.getNumPrompts(), 1000));
        Story story5 = new Story("story5");
        mission.add(new Skill(story5, story5.getNumPrompts(), 1000));
        Story story6 = new Story("story6");
        mission.add(new Skill(story6, story6.getNumPrompts(), 1000));

        mission.add(new Skill(new AndroidImages(), 200, 1000));
        return mission;
*/
    }

    public String getTitle() {
        return "language skills";
    }
}
