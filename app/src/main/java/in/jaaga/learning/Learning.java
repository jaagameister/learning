package in.jaaga.learning;

import java.util.*;

import in.jaaga.learning.missions.MathMission;
import in.jaaga.learning.missions.NegativeNumbers;


public class Learning {
    public static final int NO_RESPONSE = ChatItem.NO_RESPONSE;
    public static final int NUMBER_RESPONSE = ChatItem.NUMBER_RESPONSE;
    public static final int TEXT_RESPONSE = ChatItem.TEXT_RESPONSE;

    Random random = new Random();

    private LearningContext context;
	private int points = 0;
	private Session session;
	private ChatBot chatBot;
    private Mission mission;
    private ArrayList<Skill> path;
    private Skill skill;
	private Problem problem;

    public static int level = 0;

    public int test = 0;

	InteractionInterface interactionInterface;
    DB db;

    public Learning(LearningContext context) {
        this.context = context;
		interactionInterface = context.getInteractionInterface();
        this.db = context.getDB();
        this.session = context.getSession();
        this.chatBot = context.getChatBot();
    }

    public LearningContext getContext() {
        return this.context;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
        level = 0;
        setLevel(level);
    }

    public void setLevel(int level) {
        this.level = level;
        path = mission.getList();
        skill = path.get(level);
        session.setSkill(skill);
        problem = skill.getProblem();
    }

    public Mission getMission() {
        return mission;
    }

    public void start() {
        sendMessage(chatBot.hello(), NO_RESPONSE);
//        sendMessage(chatBot.askName(),TEXT_RESPONSE);
        sendMessage(problem.getPrompt(), NUMBER_RESPONSE);
    }

	public void onResponse(String response) {
        // TODO remimplement name when we save profiles
/*        if(false) { //session.getName() == null) {
            session.setName(response);
            if (db.containsName(response) == true) {
                sendMessage("Welcome Back " + session.getName(), NO_RESPONSE);
            } else {
                sendMessage("It seems like you are new.\n Nice to meet you ", NO_RESPONSE);
                db.addName(response);
            }
            sendMessage(problem.getPromptChatItem());
        } else
*/
        if (".".equals(response)) {
            sendMessage(chatBot.adminPrompt(), TEXT_RESPONSE);
            return;
        } else if (response.equals("whoami"))  {
            sendMessage(session.getName(),NO_RESPONSE);
            sendMessage(problem.getPromptChatItem());
            return;
        } else if (response.contains("help")) {
            sendMessage("you can say 'hint' for help with the current problem \n"+
                        "'skip will move to the next skill in the current mission\n"+
                        "mission will list the current mission and mission options", TEXT_RESPONSE);
            return;
        } else if ("hint".equals(response)) {
            sendMessage(skill.takeHint(), NO_RESPONSE);
            sendMessage(problem.getPromptChatItem());
            return;
        } else if ("skip".equals(response)) {
            skill = path.get(++level);
            session.setSkill(skill);
            problem = skill.getProblem();
            sendMessage(problem.getPromptChatItem());
            return;
        } else if (response.startsWith("mission")) {
            String missionName = response.substring("mission".length());
            Mission newMission = context.getMissionLibrary().getMission(missionName);
            if (newMission != null) {
                setMission(newMission);
                sendMessage(problem.getPromptChatItem());
            } else {
                sendMessage("the current mission is: " + mission.getTitle()+
                            "\n " + context.getMissionLibrary().getAvailableMissionsHelpStatement(), TEXT_RESPONSE);
            }
            return;
        }
        checkAnswer(response);
        sendMessage(problem.getPromptChatItem());
    }

    void checkAnswer(String response) {
        if (problem.checkAnswer(response)) {  // correct
            sendMessage(chatBot.correct(), NO_RESPONSE);
            int remains = skill.solvedOne();
            if (remains <= 0) {
                Skill last = skill;
                skill = path.get(++level);
                session.setSkill(skill);
                problem = skill.getProblem();
                session.addPoints(last.getPoints());
                sendMessage(chatBot.levelUp(last, skill), NUMBER_RESPONSE);
            } else {
                sendMessage(chatBot.comment(), NO_RESPONSE);
                problem = problem.next();
				skill.setProblem(problem);
//                sendMessage(problem.getPrompt(), NUMBER_RESPONSE);
            }
        } else {
            sendMessage(chatBot.sorry(), NO_RESPONSE);
        }
    }

    private void sendMessage(ChatItem item) {
        item.setSender("bot");
        interactionInterface.send(item);
    }

    private void sendMessage(String text, int responseType) {
        ChatItem item = new ChatItem(text, responseType);
        item.setResponseType(responseType);
        sendMessage(item);
    }
}
