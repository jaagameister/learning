package in.jaaga.learning.android;

import java.util.Random;

import in.jaaga.learning.*;

public class AndroidChatBot extends ChatBot {

	static Random random = new Random();

	Session session;

	public AndroidChatBot () {}

	public AndroidChatBot(Session session) {
		this.session = session;
	}

	public String comment() {
		if (session != null) {
			Skill skill = session.getSkill();
			if (skill != null && skill.getRemaining() == 1)
				return S.getResources().getString(R.string.one_left);
		}
		return encourage();
	}

	public String askName() {
		return S.getResources().getString(R.string.ask_name);
	}

	public String encourage() {
		return S.getResources().getString(R.string.encourage);
	}

	public String hello() {
		return S.getResources().getString(R.string.hello);
	}

	public String bye() {
		return S.getResources().getString(R.string.bye);
	}

	public String correct() {
		return S.getResources().getString(R.string.correct);
	}

	public String sorry() {
		return S.getResources().getString(R.string.incorrect);
	}

	public String levelUp(Skill last, Skill next) {
		return S.getResources().getString(R.string.level_up, last.getProblem().getTitle(),
				last.getPoints(), next.getPoints(), next.getProblem().getTitle());
    }

    public String adminPrompt() {
        return S.getResources().getString(R.string.admin_prompt);
    }
}