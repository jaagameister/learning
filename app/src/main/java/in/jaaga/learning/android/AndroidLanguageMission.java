package in.jaaga.learning.android;

import java.util.ArrayList;

import in.jaaga.learning.Skill;
import in.jaaga.learning.missions.Mission;
import in.jaaga.learning.problems.Addition;
import in.jaaga.learning.problems.DecimalAddition;
import in.jaaga.learning.problems.DecimalDivision;
import in.jaaga.learning.problems.Division;
import in.jaaga.learning.problems.DivisionRemainders;
import in.jaaga.learning.problems.Multiplication;
import in.jaaga.learning.problems.Subtraction;
import in.jaaga.learning.problems.VariableAddition;
import in.jaaga.learning.problems.VariableDivision;
import in.jaaga.learning.problems.VariableMultiplication;
import in.jaaga.learning.problems.VariableSubtraction;

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