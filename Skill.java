
public class Skill {
	int repsRequired;
	int rep;
	int points;

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
		return repsRequired - rep;
	}

	public int getPoints() {
		return points;
	}
}