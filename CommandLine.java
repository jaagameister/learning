import java.util.*;


public class CommandLine {
	static Random random = new Random();

    public static void main(String[] argv) {
		Scanner sc = new Scanner(System.in);
		ChatBot chatBot = new ChatBot();
		String command;

        System.out.println(LE.HELLO[
        		random.nextInt(LE.HELLO.length)]);

        int points = 0;
		ArrayList<Skill> mission = new ArrayList<Skill>();
		mission.add(new Skill(new Addition(10), 2, 100));
		mission.add(new Skill(new Subtraction(10), 2, 150));
		mission.add(new Skill(new Multiplication(12, 12), 3, 200));
		mission.add(new Skill(new Division(100, 10), 3, 250));
		mission.add(new Skill(new DivisionRemainders(100, 10), 4, 300));
		Iterator<Skill> path = mission.iterator();

		Skill skill = path.next();
		chatBot.setSkill(skill);
		Problem problem = skill.getProblem();
		while (true) {
			System.out.println(problem.getPrompt());
			String response = sc.nextLine();

			if ("quit".equals(response)) {
		        System.out.println(LE.BYE[
		        		random.nextInt(LE.BYE.length)]);
		        return;
			} 

			if ("hint".equals(response)) {
				System.out.println(skill.takeHint());
				response = sc.nextLine();
			}

			if (problem.checkAnswer(response)) {
		        System.out.println(LE.CORRECT[
		        		random.nextInt(LE.CORRECT.length)]);
		        int remains = skill.solvedOne();
		        if (remains <= 0) {
		        	int skillPoints = skill.getPoints();
					System.out.println("Congratulations !!! you passed " + problem.getTitle());
		        	points += skillPoints;
		        	System.out.println("and earned " + skillPoints + " skill points");
		        	System.out.println("you now have " + points + " total points");
		        	skill = path.next();
					chatBot.setSkill(skill);
		        	problem = skill.getProblem();
					System.out.println("Now its time to practice " + problem.getTitle());
		        } else {
		        	problem = problem.next();
		        }
			} else {
		        System.out.println(LE.SORRY[
		        		random.nextInt(LE.SORRY.length)]);				
			}
	        System.out.println(chatBot.getEncouragement());
		}
	}
}