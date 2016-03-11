package in.jaaga.learning;

import java.util.Random;

import in.jaaga.learning.Session;

public class ChatBot {

	public static final String[] HELLO = {
			"Hola",
			"Namaste",
			"Buenos Dias",
			"Hello"
	};

	public static final String[] BYE = {
			"Bye",
			"Later",
			"Audios",
			"Arrivederci"
	};

	public static final String[] CORRECT = {
			"Yes",
			"You got it",
			"Thats right",
			"Good Job",
			"Correct"
	};

	public static final String[] SORRY = {
			"Nope",
			"Incorrect",
			"That's not the answer I'm looking for",
			"Try Again",
			"Sorry"
	};

	public static final String[] ENCOURAGE = {
			"you're doing great",
			"keep going",
			"you're getting there",
			"keep it up"
	};

	static Random random = new Random();

	Session session;

	public ChatBot() {}

	public ChatBot(Session session) {
		this.session = session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String comment() {
		if (session != null) {
			Skill skill = session.getSkill();
//			if (skill != null && skill.getRemaining() == 1)
//				return "just " + skill.getRemaining() + " left in this skill";
		}
		return encourage();
	}

	public String askName() { return "what's your name ?"; }

	public String encourage() {
		return ENCOURAGE[random.nextInt(ENCOURAGE.length)];
	}

	public String hello() {
//		return getResources().getString(R.string.hello);
		return "hello";
	}

	public String bye() {
		return BYE[random.nextInt(BYE.length)];
	}

	public String correct() {
		return CORRECT[random.nextInt(CORRECT.length)];
	}

	public String sorry() {
		return SORRY[random.nextInt(SORRY.length)];
	}

	public String levelUp(String last, String next, int newPoints, int totalPoints) {
		StringBuffer sb = new StringBuffer();
/*		sb.append("Congratulations !!! you passed " + last.getProblem().getTitle());
		sb.append("\nand earned " + last.getPoints() + " skill points");

		session.addPoints(last.getPoints());
		sb.append("\nyou now have " + session.getPoints() + " total points");
		sb.append("\nNow its time to practice " + next.getProblem().getTitle());
*/
		return sb.toString();
	}

	public String adminPrompt() {
		return "Yes, Master...";
	}

	public String help() { return "you can say \\'hint\\' for help with the current problem\\n\n" +
			"    \\'skip\\' will move to the next skill in the current mission\\n\n" +
			"    \\'mission\\' will list the current mission and mission options";
	}

	public String invalidMission() {
		return "that is not a valid mission, try again.";
	}
}