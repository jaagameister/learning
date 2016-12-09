package in.jaaga.learning.bots.skillbot;

import java.util.ArrayList;

import in.jaaga.learning.api.ChatItem;
import in.jaaga.learning.api.Sender;

public class Mission {
    private Sender sender;
    protected ArrayList<Skill> skills;
    protected Skill skill;

    int index = 0;
    int totalPoints = 0;
    boolean initialized = false;

    public Mission(Sender sender) {
        this.sender = sender;
        skills = new ArrayList<Skill>();
    }

    public void add(Skill skill) {
        skills.add(skill);
        if (skills.size() == 1)
            this.skill = skill;
    }

    public ChatItem getPrompt() {
        ChatItem p = skill.getPromptChatItem();
        if (p == null) {
            levelUp();
            p = skill.getPromptChatItem();
        }
        return p;
    }

    public void processResponse(String response) {
        skill.processResponse(response);
    }

    public void levelUp() {
        System.out.println("mission:levelUp");
        Skill last = skill;
        Skill next = skills.get(++index);
        totalPoints += last.getPoints();
        //String levelUpText = ctx.getChatBot().levelUp(last.getTitle(), next.getTitle(), last.getPoints(), totalPoints);
        //send(new ChatItem(levelUpText));
        skill = next;
        sender.send(new ChatItem(StringUtil.levelUp(last.getTitle(), next.getTitle(), last.getPoints(), totalPoints)));
    }

    private void setLevel(String level) {
        if (level == null)
            return;
        index = Integer.parseInt(level);
        skill = skills.get(index);
        initialized = true;
    }
}