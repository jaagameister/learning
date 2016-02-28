package in.jaaga.learning;

public class Skill {
	long skillStartTime = 0;
	long skillTimeSpent = 0;
	long problemStartTime = 0;

	int repsRequired;
	int rep;
	int points;
	boolean hinted = false;


	Problem problem;

	public Skill(Problem problem, int repsRequired, int points) {
		this.problem = problem;
		this.repsRequired = repsRequired;
		this.points = points;
		skillStartTime = System.currentTimeMillis();
	}

	public Problem getProblem() {
		return problem;
	}

	public String getPrompt() {
        problemStartTime = System.currentTimeMillis();
        return problem.getPrompt();
    }

    public boolean checkAnswer(String answer) {
        if (problem.checkAnswer(answer)) {
			skillTimeSpent += System.currentTimeMillis() - problemStartTime;
			return true;
		}
		return false;
    }

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

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

	public String takeHint() {
		hinted = true;
		points = points / 2;
		return problem.getHint();
	}
}