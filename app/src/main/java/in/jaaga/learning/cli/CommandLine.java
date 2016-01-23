package in.jaaga.learning.cli;

import java.util.*;


public class CommandLine {
	static Random random = new Random();

    public static void main(String[] argv) {
		Scanner sc = new Scanner(System.in);
		Session session = new Session();
		ChatBot chatBot = new ChatBot(session);

		String command;

        System.out.println(chatBot.hello());

        int points = 0;
		ArrayList<Skill> mission = new ArrayList<Skill>();
		mission.add(new Skill(new VariableSubtraction(10), 8, 100));
		mission.add(new Skill(new VariableAddition(10), 8, 100));
		mission.add(new Skill(new Addition(10), 2, 100));
		mission.add(new Skill(new Subtraction(10), 2, 150));
		mission.add(new Skill(new Multiplication(12, 12), 3, 200));
		mission.add(new Skill(new Division(100, 10), 3, 250));
		mission.add(new Skill(new DivisionRemainders(100, 10), 4, 300));
		Iterator<Skill> path = mission.iterator();

		Skill skill = path.next();
		session.setSkill(skill);
		Problem problem = skill.getProblem();
		while (true) {
			System.out.println(problem.getPrompt());
			String response = sc.nextLine();

			if ("quit".equals(response)) {
		        System.out.println(chatBot.bye());
		        return;
			} 

			if ("hint".equals(response)) {
				System.out.println(skill.takeHint());
				System.out.println(problem.getPrompt());
				response = sc.nextLine();
			}

			if (problem.checkAnswer(response)) {
				chatBot.correct();
		        int remains = skill.solvedOne();
		        if (remains <= 0) {
		        	int skillPoints = skill.getPoints();
					System.out.println("Congratulations !!! you passed " + problem.getTitle());
		        	points += skillPoints;
		        	System.out.println("and earned " + skillPoints + " skill points");
		        	System.out.println("you now have " + points + " total points");
		        	skill = path.next();
					session.setSkill(skill);
		        	problem = skill.getProblem();
					System.out.println("Now its time to practice " + problem.getTitle());
		        } else {
		        	problem = problem.next();
		        }
			} else {
		        System.out.println(chatBot.sorry());
			}
	        System.out.println(chatBot.comment());
		}
	}
}