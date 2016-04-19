package in.jaaga.learning.bots.srini;

import java.util.HashMap;
import java.util.Random;

import in.jaaga.learning.api.ChatItem;

public class ChatBot {

	public static final String[] HELLO = {
			"Hola",
			"Namaste",
			"Buenos Dias",
			"Hello"
	};

	public static final String[] BYE = {
			"Bye",
			"Later",
			"Audios",
			"Arrivederci"
	};

	public static final String[] CORRECT = {
			"Yes",
			"You got it",
			"Thats right",
			"Good Job",
			"Correct"
	};

	public static final String[] SORRY = {
			"Nope",
			"Incorrect",
			"That's not the answer I'm looking for",
			"Try Again",
			"Sorry"
	};

	public static final String[] ENCOURAGE = {
			"you're doing great",
			"keep going",
			"you're getting there",
			"keep it up"
	};

	static Random random = new Random();

	Session session;

	public ChatBot() {}

	public ChatBot(Session session) {
		this.session = session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String comment() {
		if (session != null) {
			Skill skill = session.getSkill();
//			if (skill != null && skill.getRemaining() == 1)
//				return "just " + skill.getRemaining() + " left in this skill";
		}
		return encourage();
	}

	public String askName() { return "what's your name ?"; }

	public String encourage() {
		return ENCOURAGE[random.nextInt(ENCOURAGE.length)];
	}

	public String hello() {
//		return getResources().getString(R.string.hello);
		return "hello";
	}

	public String bye() {
		return BYE[random.nextInt(BYE.length)];
	}

	public String correct() {
		return CORRECT[random.nextInt(CORRECT.length)];
	}

	public String sorry() {
		return SORRY[random.nextInt(SORRY.length)];
	}

	public String levelUp(String last, String next, int newPoints, int totalPoints) {
		StringBuffer sb = new StringBuffer();
/*		sb.append("Congratulations !!! you passed " + last.getProblem().getTitle());
		sb.append("\nand earned " + last.getPoints() + " skill points");

		session.addPoints(last.getPoints());
		sb.append("\nyou now have " + session.getPoints() + " total points");
		sb.append("\nNow its time to practice " + next.getProblem().getTitle());
*/
		return sb.toString();
	}

	public String adminPrompt() {
		return "Yes, Master...";
	}

	public String help() { return "you can say \\'hint\\' for help with the current problem\\n\n" +
			"    \\'skip\\' will move to the next skill in the current mission\\n\n" +
			"    \\'mission\\' will list the current mission and mission options";
	}

	public String invalidMission() {
		return "that is not a valid mission, try again.";
	}

	public static class ProblemSkill implements Skill {
        public final String BOT = ChatItem.BOT;  // TODO fix this with ChatAdapter
        LearningContext ctx;

        long skillStartTime = 0;
        long skillTimeSpent = 0;
        long problemStartTime = 0;

        int repsRequired;
        int rep = 0;
        int points;
        boolean hinted = false;

        String title = null;

        Problem problem;

        public ProblemSkill(LearningContext ctx, String title, Problem problem, int repsRequired, int points) {
            this.ctx = ctx;
            this.title = title;
            this.problem = problem;
            this.repsRequired = repsRequired;
            this.points = points;
            skillStartTime = System.currentTimeMillis();
        }

        public Problem getProblem() {
            return problem;
        }

        public String getPrompt() {
            if (rep >= repsRequired)
                return null;
            problemStartTime = System.currentTimeMillis();
            return problem.getPrompt();
        }

        public ChatItem getPromptChatItem() {
            System.out.println("rep: "+rep+" req: "+repsRequired);
            if (rep >= repsRequired)
                return null;
            return problem.getPromptChatItem();
        }

        public void processResponse(String answer) {
            if (problem.checkAnswer(answer)) {
                rep++;
                skillTimeSpent += System.currentTimeMillis() - problemStartTime;
                problem = problem.next();
                send(ctx.getChatBot().correct());
            } else {
                send(ctx.getChatBot().sorry());
    //			send(getHint());
            }
            send(ctx.getChatBot().encourage());
    //		ctx.getInteractionInterface().send(problem.getPromptChatItem());
        }

        private void send(String message) {
            ctx.getInteractionInterface().send(new ChatItem(message));
        }

    //	public void setProblem(Problem problem) {
    //		this.problem = problem;
    //	}

        // increments rep and returns remaining
        public int solvedOne() {
            rep++;
            return getRemaining();
        }

        public int getPoints() {
            return points;
        }

        public long getTimeSpent() {
            return skillTimeSpent;
        }

        public long getProblemsCompleted() {
            return rep;
        }

        public int getRemaining() {
            return repsRequired - rep;
        }

        public String getHint() {
            hinted = true;
            points = points / 2;
            return problem.getHint();
        }

        public String getTitle() {
            return title;
        }

        public void save(HashMap session) {
            problem.save(session);
        }

        public void restore(HashMap session) {
            System.out.println("MathSkill.restore");
            problem.restore(session);
        }
    }

	public static class Learning {
        public static final int NO_RESPONSE = ChatItem.NO_RESPONSE;
        public static final int NUMBER_RESPONSE = ChatItem.NUMBER_RESPONSE;
        public static final int TEXT_RESPONSE = ChatItem.TEXT_RESPONSE;

        LearningContext ctx;

        public Learning() {}

        public Learning(LearningContext context) {
            ctx = context;
        }

        public void start() {
            if (ctx.getSession().isEmpty())
                ctx.getInteractionInterface().send(new ChatItem(ctx.getChatBot().hello(), NO_RESPONSE));
            else
                ctx.getInteractionInterface().send(new ChatItem(ctx.getChatBot().encourage(), NO_RESPONSE));
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
                sendMessage(ctx.getChatBot().help(), TEXT_RESPONSE);
                return true;
            } else if ("hint".equals(response)) {
                sendMessage("no more hints ..", NO_RESPONSE);
//                sendMessage(ctx.getMission().getHint(), NO_RESPONSE);
                sendMessage(ctx.getMission().getPrompt());
                return true;
            } else if ("skip".equals(response)) {
                ctx.getMission().levelUp();
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
//            ctx.getMission().save();
        }

        public void restore() {
            if (ctx.getSession().size() > 0 && ctx.getSession().get("mission") != null) {
                setMission(ctx.getSession().get("mission"));
            }
  //          ctx.getMission().restore();
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
}