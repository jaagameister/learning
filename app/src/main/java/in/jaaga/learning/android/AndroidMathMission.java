package in.jaaga.learning.android;

import android.content.res.Resources;

import java.util.ArrayList;

import in.jaaga.learning.Mission;
import in.jaaga.learning.R;
import in.jaaga.learning.Skill;
import in.jaaga.learning.android.problems.Story;
import in.jaaga.learning.problems.Addition;
import in.jaaga.learning.problems.DecimalAddition;
import in.jaaga.learning.problems.DecimalDivision;
import in.jaaga.learning.problems.DecimalMultiplication;
import in.jaaga.learning.problems.Division;
import in.jaaga.learning.problems.DivisionRemainders;
import in.jaaga.learning.problems.Multiplication;
import in.jaaga.learning.problems.Subtraction;
import in.jaaga.learning.problems.VariableAddition;
import in.jaaga.learning.problems.VariableDivision;
import in.jaaga.learning.problems.VariableMultiplication;
import in.jaaga.learning.problems.VariableSubtraction;

/**
 * Created by freeman on 1/3/16.
 */
public class AndroidMathMission implements Mission {
    public ArrayList<Skill> getList() {
        ArrayList<Skill> mission = new ArrayList<Skill>();

        Resources r = S.getResources();

        mission.add(new Skill(r.getString(R.string.addition_title, 10), new Addition(10), 5, 100));
        mission.add(new Skill(r.getString(R.string.addition_title, 100), new Addition(100), 5, 100));

        mission.add(new Skill(r.getString(R.string.subtraction_title, 10), new Subtraction(10), 5, 100));
        mission.add(new Skill(r.getString(R.string.subtraction_title, 100), new Subtraction(100), 5, 150));

        mission.add(new Skill(r.getString(R.string.multiplication_title, 5), new Multiplication(5, 5), 10, 200));
        mission.add(new Skill(r.getString(R.string.multiplication_title, 10), new Multiplication(10, 10), 10, 200));

        mission.add(new Skill(r.getString(R.string.addition_title, 1000), new Addition(1000), 5, 150));

        mission.add(new Skill(r.getString(R.string.division_title, 30, 10), new Division(30, 10), 5, 250));
        mission.add(new Skill(r.getString(R.string.division_title, 100, 10), new Division(100, 10), 5, 250));

        mission.add(new Skill(r.getString(R.string.addition_negative_title, 10), new Addition(-10), 5, 100));
        mission.add(new Skill(r.getString(R.string.subtraction_negative_title, 10), new Subtraction(-10), 5, 150));
        mission.add(new Skill(r.getString(R.string.multiplication_negative_title, 30), new Multiplication(-12, 12), 5, 200));

        mission.add(new Skill(r.getString(R.string.decimal_addition_title, 100), new DecimalAddition(100, 0, 1), 5, 100));
        mission.add(new Skill(r.getString(R.string.decimal_multiplication_title, 100), new DecimalMultiplication(100,10),5,100));
        mission.add(new Skill(r.getString(R.string.decimal_division_title, 100, 10), new DecimalDivision(100,10),5,100));

        mission.add(new Skill(r.getString(R.string.var_addition_title, 100), new VariableAddition(100), 8, 100));
        mission.add(new Skill(r.getString(R.string.var_subtraction_title, 100), new VariableSubtraction(100), 8, 100));
        mission.add(new Skill(r.getString(R.string.var_multiplication_title, 10), new VariableMultiplication(10, 10), 8, 100));
        mission.add(new Skill(r.getString(R.string.var_division_title, 100, 10), new VariableDivision(100, 10), 8, 100));

        return mission;
    }

    public String getTitle() {
        return S.getActivity().getResources().getString(R.string.basic_math_mission);
    }
}
