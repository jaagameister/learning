package in.jaaga.learning.android;

import java.util.Properties;
import java.util.Random;

import in.jaaga.learning.*;

public class AndroidChatBot extends ChatBot {
	static Random random = new Random();

	public AndroidChatBot() {}

	public String oneLeft() {
		String[] oneLeft = S.getResources().getStringArray(R.array.one_left);
		return oneLeft[random.nextInt(oneLeft.length)];
	}

	public String hello() {
		String[] hello = S.getResources().getStringArray(R.array.hello);
		return hello[random.nextInt(hello.length)];
	}

	public String askName() {
		String[] askName = S.getResources().getStringArray(R.array.ask_name);
		return askName[random.nextInt(askName.length)];
	}

	public String bye() {
		String[] bye = S.getResources().getStringArray(R.array.bye);
		return bye[random.nextInt(bye.length)];
	}

	public String correct() {
		String[] correct = S.getResources().getStringArray(R.array.correct );
		return correct[random.nextInt(correct.length)];
	}

	public String sorry() { // as in "Sorry, incorrect"
		String[] incorrect = S.getResources().getStringArray(R.array.incorrect );
		return incorrect[random.nextInt(incorrect.length)];
	}

	public String encourage() {
		String[] encourage = S.getResources().getStringArray(R.array.encourage);
		return encourage[random.nextInt(encourage.length)];
	}

	public String levelUp(String last, String next, int newPoints, int totalPoints) {
		return S.getResources().getString(R.string.level_up, last,
				newPoints, totalPoints, next);
	}

	public String adminPrompt() {
		String[] adminPrompt = S.getResources().getStringArray(R.array.admin_prompt);
		return adminPrompt[random.nextInt(adminPrompt.length)];
	}

	public String invalidMission() {
		return S.getResources().getString(R.string.invalid_mission);
	}
}