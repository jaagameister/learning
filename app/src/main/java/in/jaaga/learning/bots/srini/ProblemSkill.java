package in.jaaga.learning.bots.srini;

import java.util.HashMap;
import java.util.Properties;

import in.jaaga.learning.api.ChatItem;
import in.jaaga.learning.api.Sender;
import in.jaaga.learning.bots.srini.Problem;
import in.jaaga.learning.bots.srini.Skill;

public class ProblemSkill implements Skill {
	Sender sender;

	public final String BOT = ChatItem.BOT;  // TODO fix this with ChatAdapter

	long skillStartTime = 0;
	long skillTimeSpent = 0;
	long problemStartTime = 0;

	int repsRequired;
	int rep = 0;
	int points;
	boolean hinted = false;

	Problem problem;

	public ProblemSkill(Sender sender, Problem problem, int repsRequired, int points) {
        this.sender = sender;
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
            sender.send(new ChatItem("correct"));
		} else {
            sender.send(new ChatItem("sorry, no."));
        }
		sender.send(new ChatItem("keep going"));
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
        return "title";
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