package in.jaaga.learning;

import java.util.*;

import in.jaaga.learning.Session;
import in.jaaga.learning.problems.Addition;
import in.jaaga.learning.problems.DecimalAddition;
import in.jaaga.learning.problems.Division;
import in.jaaga.learning.problems.DivisionRemainders;
import in.jaaga.learning.problems.Multiplication;
import in.jaaga.learning.problems.NumbersSequence;
import in.jaaga.learning.problems.Subtraction;
import in.jaaga.learning.problems.VariableAddition;
import in.jaaga.learning.problems.VariableDivision;
import in.jaaga.learning.problems.VariableMultiplication;
import in.jaaga.learning.problems.VariableSubtraction;


public class Learning {
    public static final int NO_RESPONSE = 0;
    public static final int NUMBER_RESPONSE = 1;
    public static final int TEXT_RESPONSE = 2;

	Random random = new Random();

	private int points = 0;
	private Session session;
	private ChatBot chatBot;
    private Iterator<Skill> path;
    private Skill skill;
	private Problem problem;

	InteractionInterface interactionInterface;

	ArrayList<Skill> buildMission() {
        ArrayList<Skill> mission = new ArrayList<Skill>();

		mission.add(new Skill(new NumbersSequence(1), 5, 100));

		mission.add(new Skill(new Addition(10), 5, 100));
		mission.add(new Skill(new Addition(100), 5, 150));
		mission.add(new Skill(new Subtraction(10), 5, 100));
		mission.add(new Skill(new Subtraction(100), 5, 150));

		mission.add(new Skill(new Multiplication(5, 5), 10, 200));
		mission.add(new Skill(new Multiplication(10, 10), 10, 200));
		mission.add(new Skill(new Addition(1000), 5, 150));

		mission.add(new Skill(new Division(30, 10), 5, 250));
		mission.add(new Skill(new Division(100, 10), 5, 250));
		mission.add(new Skill(new DivisionRemainders(30, 10), 5, 300));
		mission.add(new Skill(new Subtraction(1000), 5, 100));
		mission.add(new Skill(new Multiplication(100, 10), 10, 200));

		mission.add(new Skill(new Addition(-10), 5, 100));
		mission.add(new Skill(new Subtraction(-10), 5, 150));
		mission.add(new Skill(new Multiplication(-12, 12), 5, 200));

		mission.add(new Skill(new DecimalAddition(0, 9, 1), 5, 100));
		mission.add(new Skill(new VariableDivision(100, 10), 8, 100));
		mission.add(new Skill(new VariableMultiplication(10, 10), 8, 100));
		mission.add(new Skill(new VariableSubtraction(10), 8, 100));
		mission.add(new Skill(new VariableAddition(10), 8, 100));
		mission.add(new Skill(new Addition(10), 2, 100));
		mission.add(new Skill(new Subtraction(10), 2, 150));
		mission.add(new Skill(new Multiplication(12, 12), 3, 200));
		mission.add(new Skill(new Division(100, 10), 3, 250));
		mission.add(new Skill(new DivisionRemainders(100, 10), 4, 300));

		return mission;
	}

    public Learning(InteractionInterface minteractionInterface) {
		interactionInterface = minteractionInterface;
        Scanner sc = new Scanner(System.in);
        session = new Session();
        chatBot = new ChatBot(session);

        path = buildMission().iterator();
        skill = path.next();
        session.setSkill(skill);
        problem = skill.getProblem();
    }

	public void start() {
        sendMessage(chatBot.hello(), NO_RESPONSE);
        sendMessage(problem.getPrompt(), NUMBER_RESPONSE);
    }

	public void onResponse(String response){
		if (".".equals(response)) {
			sendMessage(chatBot.adminPrompt(), TEXT_RESPONSE);
			return;
		}

		if ("hint".equals(response)) {
			sendMessage(skill.takeHint(), NO_RESPONSE);
			sendMessage(problem.getPrompt(), NUMBER_RESPONSE);
			return;
		}

        if ("skip".equals(response)) {
            skill = path.next();
            session.setSkill(skill);
            problem = skill.getProblem();
            sendMessage(problem.getPrompt(), NUMBER_RESPONSE);
            return;
        }

        if ("mission".equals(response)) {
            return;
        }

        checkAnswer(response);
        sendMessage(problem.getPrompt(), NUMBER_RESPONSE);
	}

    void checkAnswer(String response) {
        if (problem.checkAnswer(response)) {  // correct
            sendMessage(chatBot.correct(), NO_RESPONSE);
            int remains = skill.solvedOne();
            if (remains <= 0) {
                Skill last = skill;
                skill = path.next();
                session.setSkill(skill);
                problem = skill.getProblem();
//				System.out.println("KS");
//                sendMessage(chatBot.levelUp(last, skill), NUMBER_RESPONSE,  R.drawable.ks);
//                sendMessage(chatBot.levelUp(last, skill), NUMBER_RESPONSE);
            } else {
//                System.out.println(chatBot.comment());
                sendMessage(chatBot.comment(), NO_RESPONSE);
                problem = problem.next();
				skill.setProblem(problem);
//                sendMessage(problem.getPrompt(), NUMBER_RESPONSE);
            }
        } else {
//            System.out.println(chatBot.sorry());
            sendMessage(chatBot.sorry(), NO_RESPONSE);
        }
    }

    private void sendMessage(String text, int responseType) {
        sendMessage(text, responseType, -1);
    }

    private void sendMessage(String text, int responseType, int imageResourceId) {
        ChatItem item = new ChatItem();
        item.setMessage(text);
        item.setSender("bot");
        item.setResponseType(responseType);
//		item.setResourceId(R.drawable.ks);
		item.setResourceId(imageResourceId);
		interactionInterface.send(item);
    }
}
