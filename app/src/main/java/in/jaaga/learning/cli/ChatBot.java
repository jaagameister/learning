package in.jaaga.learning.cli;

import java.util.Random;

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

	public ChatBot(Session session) {
		this.session = session;
	}

	public String comment() {
		Skill skill = session.getSkill();
		if (skill != null && skill.getRemaining() == 1)
			return "just " + skill.getRemaining() + " left in this skill";

		if (session.getName() != null) {
			return session.getName() + ", " + encourage();
		}
		return encourage();
	}

	public String askName() { return "what's your name ?"; }

	public String encourage() {
		return ENCOURAGE[random.nextInt(ENCOURAGE.length)];
	}

	public String hello() {
        String name = session.getName();
		if (name != null)
			return HELLO[random.nextInt(HELLO.length)] + ", " + name;
		return HELLO[random.nextInt(HELLO.length)];
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

	public String levelUp(Skill last, Skill next) {
        StringBuffer sb = new StringBuffer();
        sb.append("Congratulations !!! you passed " + last.getProblem().getTitle());
        sb.append("\nand earned " + last.getPoints() + " skill points");

        session.addPoints(last.getPoints());
        sb.append("\nyou now have " + session.getPoints() + " total points");
        sb.append("\nNow its time to practice " + next.getProblem().getTitle());

        return sb.toString();
    }
}