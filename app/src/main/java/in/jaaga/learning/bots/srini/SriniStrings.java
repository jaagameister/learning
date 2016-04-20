package in.jaaga.learning.bots.srini;

import android.content.res.Resources;

import java.util.Random;

import in.jaaga.learning.*;

public class SriniStrings {
	static Random random = new Random();
	static Resources res;

	public static void setResources(Resources resources) {
		res = resources;
	}

	public static String oneLeft() {
		String[] oneLeft = res.getStringArray(R.array.one_left);
		return oneLeft[random.nextInt(oneLeft.length)];
	}

	public static String hello() {
		String[] hello = res.getStringArray(R.array.hello);
		return hello[random.nextInt(hello.length)];
	}

	public static String askName() {
		String[] askName = res.getStringArray(R.array.ask_name);
		return askName[random.nextInt(askName.length)];
	}

	public static String bye() {
		String[] bye = res.getStringArray(R.array.bye);
		return bye[random.nextInt(bye.length)];
	}

	public static String correct() {
		String[] correct = res.getStringArray(R.array.correct);
		return correct[random.nextInt(correct.length)];
	}

	public static String sorry() { // as in "Sorry, incorrect"
		String[] incorrect = res.getStringArray(R.array.incorrect);
		return incorrect[random.nextInt(incorrect.length)];
	}

	public static String encourage() {
		String[] encourage = res.getStringArray(R.array.encourage);
		return encourage[random.nextInt(encourage.length)];
	}

	public static String levelUp(String last, String next, int newPoints, int totalPoints) {
		return res.getString(R.string.level_up, last,
				newPoints, totalPoints, next);
	}

	public static String adminPrompt() {
		String[] adminPrompt = res.getStringArray(R.array.admin_prompt);
		return adminPrompt[random.nextInt(adminPrompt.length)];
	}

	public static String invalidMission() {
		return res.getString(R.string.invalid_mission);
	}
}