package in.jaaga.learning.cli;

import java.util.*;

import in.jaaga.learning.InteractionInterface;
import in.jaaga.learning.pojo.ChatItem;


public class CommandLine {
	static Random random = new Random();

	private static int points = 0;
	private static Session session;
	private static ChatBot chatBot;
	private static ArrayList<Skill> mission;
	private static Skill skill;
	private static Iterator<Skill> path;
	private static Problem problem;

	static InteractionInterface interactionInterface;

    public static void main(String[] argv,InteractionInterface minteractionInterface) {
		interactionInterface = minteractionInterface;

		Scanner sc = new Scanner(System.in);
		session = new Session();
		chatBot = new ChatBot(session);

		String command;

        System.out.println(chatBot.hello());
		sendMessage(chatBot.hello());

		mission = new ArrayList<Skill>();
		mission.add(new Skill(new VariableSubtraction(10), 8, 100));
		mission.add(new Skill(new VariableAddition(10), 8, 100));
		mission.add(new Skill(new Addition(10), 2, 100));
		mission.add(new Skill(new Subtraction(10), 2, 150));
		mission.add(new Skill(new Multiplication(12, 12), 3, 200));
		mission.add(new Skill(new Division(100, 10), 3, 250));
		mission.add(new Skill(new DivisionRemainders(100, 10), 4, 300));
		path = mission.iterator();

		skill = path.next();
		session.setSkill(skill);
		problem = skill.getProblem();

		System.out.println(problem.getPrompt());
		sendMessage(problem.getPrompt());


	}

	public static void onResponse(String response){

		if ("quit".equals(response)) {
			System.out.println(chatBot.bye());
			return;
		}

		if ("hint".equals(response)) {
			System.out.println(skill.takeHint());
			System.out.println(problem.getPrompt());

		}

		if (problem.checkAnswer(response)) {
			chatBot.correct();
			int remains = skill.solvedOne();
			if (remains <= 0) {
				int skillPoints = skill.getPoints();
				System.out.println("Congratulations !!! you passed " + problem.getTitle());
				sendMessage("Congratulations !!! you passed " + problem.getTitle());
				points += skillPoints;
				System.out.println("and earned " + skillPoints + " skill points");
				sendMessage("and earned " + skillPoints + " skill points");
				System.out.println("you now have " + points + " total points");
				sendMessage("you now have " + points + " total points");
				skill = path.next();
				session.setSkill(skill);
				problem = skill.getProblem();
				System.out.println("Now its time to practice " + problem.getTitle());
				sendMessage("Now its time to practice " + problem.getTitle());
			} else {
				problem = problem.next();
			}
		} else {
			System.out.println(chatBot.sorry());
			sendMessage(chatBot.sorry());
		}
		System.out.println(chatBot.comment());
		sendMessage(chatBot.comment());

		sendMessage(problem.getPrompt());

	}

	private static void sendMessage(String text){


		ChatItem item = new ChatItem();
		item.setMessage(text);
		item.setSender("bot");
		interactionInterface.Send(item);

	}
}