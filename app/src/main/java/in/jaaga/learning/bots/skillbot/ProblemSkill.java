package in.jaaga.learning.bots.skillbot;

import java.util.HashMap;

import in.jaaga.learning.api.Bot;
import in.jaaga.learning.api.ChatItem;
import in.jaaga.learning.api.Sender;

public class ProblemSkill implements Skill {
	Sender sender;

	public final String BOT = ChatItem.BOT;  // TODO fix this with ChatAdapter

	long skillStartTime = 0;
	long skillTimeSpent = 0;
	long problemStartTime = 0;

    String title;

	int repsRequired;
	int rep = 0;
	int points;
	boolean hinted = false;

	Problem problem;
	Bot bot;

	public ProblemSkill(Bot bot, String title, Problem problem, int repsRequired, int points) {
        this.bot = bot;
        this.title = title;
        sender = bot.sender;
		this.problem = problem;
        this.repsRequired = repsRequired;
        this.points = points;
        skillStartTime = System.currentTimeMillis();
	}

	public Problem getProblem() {
		return problem;
	}

	public String getPrompt() {
        if (rep >= repsRequired)
            return null;
        problemStartTime = System.currentTimeMillis();
		return problem.getPrompt();
    }

	public ChatItem getPromptChatItem() {
		if (rep >= repsRequired)
			return null;
		return problem.getPromptChatItem();
	}

    public void processResponse(String answer) {
        if (problem.checkAnswer(answer)) {
            rep++;
			skillTimeSpent += System.currentTimeMillis() - problemStartTime;
			problem = problem.next();
            sender.send(new ChatItem(StringUtil.correct()));
		} else {
            sender.send(new ChatItem(StringUtil.sorry()));
        }
		sender.send(new ChatItem(StringUtil.encourage()));
    }

	public int getPoints() {
		return points;
	}

	public String getHint() {
		hinted = true;
		points = points / 2;
		return problem.getHint();
	}

    public String getTitle() {
        return title;
    }

	public void save(HashMap session) {
        problem.save(session);
    }

    public void restore(HashMap session) {
		System.out.println("MathSkill.restore");
		new Exception().printStackTrace();
        problem.restore(session);
    }
}