package in.jaaga.learning;

import java.util.HashMap;
import java.util.Properties;

public class ProblemSkill implements Skill {
	public final String BOT = ChatItem.BOT;  // TODO fix this with ChatAdapter
	LearningContext ctx;

	long skillStartTime = 0;
	long skillTimeSpent = 0;
	long problemStartTime = 0;

	int repsRequired;
	int rep = 0;
	int points;
	boolean hinted = false;

	String title = null;

	Problem problem;

	public ProblemSkill(LearningContext ctx, String title, Problem problem, int repsRequired, int points) {
		this.ctx = ctx;
		this.title = title;
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
            send(ctx.getChatBot().correct());
		} else {
            send(ctx.getChatBot().sorry());
			send(getHint());
        }
		send(ctx.getChatBot().encourage());
//		ctx.getInteractionInterface().send(problem.getPromptChatItem());
    }

	private void send(String message) {
		ctx.getInteractionInterface().send(new ChatItem(message));
	}

//	public void setProblem(Problem problem) {
//		this.problem = problem;
//	}

	// increments rep and returns remaining
	public int solvedOne() {
		rep++;
		return getRemaining();
	}

	public int getPoints() {
		return points;
	}

	public long getTimeSpent() {
		return skillTimeSpent;
	}

	public long getProblemsCompleted() {
		return rep;
	}

	public int getRemaining() {
		return repsRequired - rep;
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
        problem.restore(session);
    }
}