package in.jaaga.learning;

import java.util.*;

import in.jaaga.learning.missions.General;
import in.jaaga.learning.missions.Mission;
import in.jaaga.learning.missions.NegativeNumbers;
//import in.jaaga.learning.problems.NumbersSequence;


public class Learning {
    public static final int NO_RESPONSE = 0;
    public static final int NUMBER_RESPONSE = 1;
    public static final int TEXT_RESPONSE = 2;

	Random random = new Random();

	private int points = 0;
	private Session session;
	private ChatBot chatBot;
    private Mission mission;
    private Iterator<Skill> path;
    private Skill skill;
	private Problem problem;

	InteractionInterface interactionInterface;


    public Learning(InteractionInterface minteractionInterface, ChatBot chatBot) {
		interactionInterface = minteractionInterface;
        Scanner sc = new Scanner(System.in);
        session = new Session();
        this.chatBot = chatBot;
        this.chatBot.setSession(session);
//        chatBot = new ChatBot(session);
        setMission(new General());
    }

    public void setMission(Mission mission) {
        this.mission = mission;
        path = mission.getList().iterator();
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
		} else if (response.contains("help")) {
            sendMessage("you can say 'hint' for help with the current problem \n"+
                        "'skip will move to the next skill in the current mission\n"+
                        "mission will list the current mission and mission options", TEXT_RESPONSE);
        } else if ("hint".equals(response)) {
                sendMessage(skill.takeHint(), NO_RESPONSE);
                sendMessage(problem.getPrompt(), NUMBER_RESPONSE);
                return;
        } else if ("skip".equals(response)) {
            skill = path.next();
            session.setSkill(skill);
            problem = skill.getProblem();
            sendMessage(problem.getPrompt(), NUMBER_RESPONSE);
            return;
        } else if (response.startsWith("mission")) {
            if (response.contains("general")) {
                setMission(new General());
                sendMessage(problem.getPrompt(), NUMBER_RESPONSE);
            } else if (response.contains("negative")) {
                setMission(new NegativeNumbers());
                sendMessage(problem.getPrompt(), NUMBER_RESPONSE);
            } else if (response.contains("easy")) {
                setMission(new NegativeNumbers());
                sendMessage(problem.getPrompt(), NUMBER_RESPONSE);
            } else {
                sendMessage("the current mission is: " + mission.getTitle()+
                            "\n available missions are: general and negative." +
                            " type - mission negative - to switch.", TEXT_RESPONSE);
            }
        } else {
            checkAnswer(response);
            sendMessage(problem.getPrompt(), NUMBER_RESPONSE);
        }
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
                session.addPoints(last.getPoints());
                sendMessage(chatBot.levelUp(last, skill), NUMBER_RESPONSE);
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
