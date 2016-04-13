package in.jaaga.learning.android;

import android.content.res.Resources;

import java.util.ArrayList;

import in.jaaga.learning.LearningContext;
import in.jaaga.learning.ProblemSkill;
import in.jaaga.learning.Mission;
import in.jaaga.learning.ProblemSkill;
import in.jaaga.learning.R;
import in.jaaga.learning.Skill;
import in.jaaga.learning.android.problems.Story;
import in.jaaga.learning.problems.Addition;
//import in.jaaga.learning.problems.DecimalAddition;
import in.jaaga.learning.problems.DecimalDivision;
import in.jaaga.learning.problems.DecimalMultiplication;
import in.jaaga.learning.problems.Division;
import in.jaaga.learning.problems.DivisionRemainders;
import in.jaaga.learning.problems.Equation;
import in.jaaga.learning.problems.Fraction;
import in.jaaga.learning.problems.Function;
import in.jaaga.learning.problems.Multiplication;
import in.jaaga.learning.problems.Percent;
import in.jaaga.learning.problems.RoundingProblems;
import in.jaaga.learning.problems.Subtraction;
import in.jaaga.learning.problems.VariableAddition;
import in.jaaga.learning.problems.VariableDivision;
import in.jaaga.learning.problems.VariableMultiplication;
import in.jaaga.learning.problems.VariableSubtraction;

/**
 * Created by freeman on 1/3/16.
 */
public class AndroidMathMission extends Mission {

    public AndroidMathMission(LearningContext ctx) {
        super(ctx);
        Resources r = S.getResources();

        add(new ProblemSkill(ctx, r.getString(R.string.addition_title, 10), new Addition(10), 5, 100));
        add(new ProblemSkill(ctx, r.getString(R.string.addition_title, 100), new Addition(100), 5, 100));

        add(new ProblemSkill(ctx, r.getString(R.string.subtraction_title, 10), new Subtraction(10), 5, 100));
        add(new ProblemSkill(ctx, r.getString(R.string.subtraction_title, 100), new Subtraction(100), 5, 150));

        add(new ProblemSkill(ctx, r.getString(R.string.multiplication_title, 5), new Multiplication(5, 5), 5, 200));
        add(new ProblemSkill(ctx, r.getString(R.string.multiplication_title, 10), new Multiplication(10, 10), 5, 200));

        add(new ProblemSkill(ctx, r.getString(R.string.addition_title, 1000), new Addition(1000), 5, 150));

        add(new ProblemSkill(ctx, r.getString(R.string.division_title, 30, 10), new Division(30, 10), 5, 250));
        add(new ProblemSkill(ctx, r.getString(R.string.division_title, 100, 10), new Division(100, 10), 5, 250));

        add(new ProblemSkill(ctx, r.getString(R.string.addition_negative_title, 10), new Addition(-10), 5, 100));
        add(new ProblemSkill(ctx, r.getString(R.string.subtraction_negative_title, 10), new Subtraction(-10), 5, 150));
        add(new ProblemSkill(ctx, r.getString(R.string.multiplication_negative_title, 12), new Multiplication(-12, 12), 5, 200));

//        add(new MathSkill(ctx, r.getString(R.string.decimal_addition_title, 100), new DecimalAddition(100, 0, 1), 5, 100));
        add(new ProblemSkill(ctx, r.getString(R.string.decimal_multiplication_title, 100), new DecimalMultiplication(100,5),5,100));
        add(new ProblemSkill(ctx,r.getString(R.string.rounding_title),new RoundingProblems(),5,50));
        add(new ProblemSkill(ctx, r.getString(R.string.decimal_division_title, 100, 10), new DecimalDivision(100,5),5,100));
        add(new ProblemSkill(ctx, r.getString(R.string.fractions_title,10), new Fraction(100),5,100));

        add(new ProblemSkill(ctx, r.getString(R.string.var_addition_title, 100), new VariableAddition(100), 8, 100));
        add(new ProblemSkill(ctx, r.getString(R.string.var_subtraction_title, 100), new VariableSubtraction(100), 8, 100));

        add(new ProblemSkill(ctx, r.getString(R.string.var_multiplication_title, 10), new VariableMultiplication(10, 10), 8, 100));
        add(new ProblemSkill(ctx, r.getString(R.string.var_division_title, 100, 10), new VariableDivision(100, 10), 8, 100));

        add(new ProblemSkill(ctx, r.getString(R.string.addition_title, 10), new Equation(), 10, 100));
        add(new ProblemSkill(ctx, r.getString(R.string.addition_title, 10), new Function(), 10, 100));
        add(new ProblemSkill(ctx,r.getString(R.string.percentages_title),new Percent(1000),5,200));
    }

    public String getTitle() {
        return S.getActivity().getResources().getString(R.string.basic_math_mission);
    }

    public String getHint() {
        return skill.getHint();
    }
}
