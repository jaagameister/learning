package in.jaaga.learning;

import java.util.*;

//import in.jaaga.learning.missions.MathMission;
//import in.jaaga.learning.missions.NegativeNumbers;


public class Learning {
    public static final int NO_RESPONSE = ChatItem.NO_RESPONSE;
    public static final int NUMBER_RESPONSE = ChatItem.NUMBER_RESPONSE;
    public static final int TEXT_RESPONSE = ChatItem.TEXT_RESPONSE;

    LearningContext ctx;

    public Learning() {}

    public Learning(LearningContext context) {
        ctx = context;
    }

    public void start() {
        ctx.getInteractionInterface().send(new ChatItem(ctx.chatBot.hello(), NO_RESPONSE));
        ctx.getInteractionInterface().send(ctx.getMission().getPrompt());
    }

	public void onResponse(String response) {
        if (!adminResponse(response)) {
            ctx.getMission().processResponse(response);
            ctx.getInteractionInterface().send(ctx.getMission().getPrompt());
        }
    }

    public boolean adminResponse(String response) {
        if (".".equals(response)) {
            sendMessage(ctx.getChatBot().adminPrompt(), TEXT_RESPONSE);
            return true;
        } else if ("whoami".equals(response))  {
            sendMessage(ctx.getSession().get("name"),NO_RESPONSE);
            sendMessage(ctx.getMission().getPrompt());
            return true;
        } else if ("help".equals(response)) {
            sendMessage(ctx.chatBot.help(), TEXT_RESPONSE);
            return true;
        } else if ("hint".equals(response)) {
//            sendMessage("no more hints ..", NO_RESPONSE);
            sendMessage(ctx.getMission().getHint(), NO_RESPONSE);
            sendMessage(ctx.getMission().getPrompt());
            return true;
        } else if ("skip".equals(response)) {
            ctx.mission.levelUp();
            sendMessage(ctx.getMission().getPrompt());
            return true;
        } else if (response.startsWith("mission")) {
            if (setMission(response.substring("mission".length()))) {
                sendMessage(ctx.getMission().getPrompt());
            } else {
                sendMessage(ctx.getChatBot().invalidMission(), TEXT_RESPONSE);
            }
            return true;
        }
        return false;
    }

    private boolean setMission(String missionName) {
        Mission m = ctx.getMissionLibrary().getMission(missionName);
        if (m != null) {
            ctx.setMission(m);
            ctx.getSession().put("mission", missionName);
            return true;
        } else {
            return false;
        }
    }

    public void save() {
        ctx.getMission().save();
    }

    public void restore() {
        if (ctx.getSession().size() > 0 && ctx.getSession().get("mission") != null) {
            setMission(ctx.getSession().get("mission"));
        }
        ctx.getMission().restore();
    }
/*
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
*/
    private void sendMessage(ChatItem item) {
        ctx.getInteractionInterface().send(item);
    }

    private void sendMessage(String text, int responseType) {
        ctx.getInteractionInterface().send(new ChatItem(text, responseType));
    }
}
