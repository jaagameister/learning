
public class Skill extends Problem {
	int repsRequired;
	int rep;
	int points;

	Problem problem;

	public Skill(Problem problem, int repsRequired, int points) {
		this.problem = problem;
		this.repsRequired = repsRequired;
		this.points = points;
	}

	public void setPrompt(String prompt) {
		problem.setPrompt(prompt);
	}

	public String getPrompt() {
		return problem.getPrompt();
	}

	public boolean checkAnswer(String answer) {
		boolean correct = problem.checkAnswer(answer);
		if (correct) 
			rep++;
		return correct;
	}

	public void nextProblem() {
		problem = problem.next();
	}

	public String getTitle() {
		return problem.getTitle();
	}

	public int completed() {		
		if (rep >= repsRequired) {
			return points;
		} else 
			return 0;
	}
}