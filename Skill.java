
public class Skill {
	int repsRequired;
	int rep;
	int points;
	boolean hinted = false;

	Problem problem;

	public Skill(Problem problem, int repsRequired, int points) {
		this.problem = problem;
		this.repsRequired = repsRequired;
		this.points = points;
	}

	public Problem getProblem() {
		return problem;
	}

	// increments rep and returns remaining
	public int solvedOne() {
		rep++;
		return getRemaining();
	}

	public int getPoints() {
		return points;
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