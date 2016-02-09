package in.jaaga.learning;

import android.content.res.Resources;

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

	public ChatBot(Session session) {
		this.session = session;
	}

	public String comment() {
		Skill skill = session.getSkill();
		if (skill != null && skill.getRemaining() == 1)
			return S.RESOURCES.getString(R.string.one_left);
		return encourage();
	}

	public String askName() {
		return S.RESOURCES.getString(R.string.ask_name);
	}

	public String encourage() {
		return S.RESOURCES.getString(R.string.encourage);
	}

	public String hello() {
		return S.RESOURCES.getString(R.string.hello);
	}

	public String bye() {
		return S.RESOURCES.getString(R.string.bye);
	}

	public String correct() {
		return S.RESOURCES.getString(R.string.correct);
	}

	public String sorry() {
		return S.RESOURCES.getString(R.string.incorrect);
	}

	public String levelUp(Skill last, Skill next) {
		session.addPoints(last.getPoints());
		return S.RESOURCES.getString(R.string.level_up, last.getProblem().getTitle(),
				last.getPoints(), next.getPoints(), next.getProblem().getTitle());
    }

    public String adminPrompt() {
        return S.RESOURCES.getString(R.string.admin_prompt);
    }
}