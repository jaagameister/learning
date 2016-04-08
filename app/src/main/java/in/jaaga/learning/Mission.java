package in.jaaga.learning;

import java.util.ArrayList;
import in.jaaga.learning.Skill;

public abstract class Mission {
    protected ArrayList<Skill> skills;
    protected Skill skill;
    LearningContext ctx;

    int index = 0;
    int totalPoints = 0;
    boolean initialized = false;

    public Mission(LearningContext ctx) {
        this.ctx = ctx;
        skills = new ArrayList<Skill>();
    }

    public void add(Skill skill) {
        skills.add(skill);
        if (skills.size() == 1)
            this.skill = skill;
    }

    public ChatItem getPrompt() {
        if (!initialized) {
            initialized = true;
            restore();
        }
        ChatItem p = skill.getPromptChatItem();
        if (p != null) {
            return p;
        } else {
            levelUp();
            return skill.getPromptChatItem();
        }
    }

    public void processResponse(String response) {
        if (skill == null)
            setLevel(ctx.getSession().get("level"));
        skill.processResponse(response);
    }

    public void save() {
        ctx.getSession().put("level", Integer.valueOf(index).toString());
        ctx.getSession().put("points", Integer.valueOf(totalPoints).toString());
        skill.save(ctx.getSession());
    }

    public void restore() {
        if ( ctx.getSession().size() == 0)
            return;
        setLevel(ctx.getSession().get("level"));
        skill.restore(ctx.getSession());
    }

    abstract public String getTitle();

    public String getHint() {
        return skill.getHint();
    }

    protected void levelUp() {
        System.out.println("mission:levelUp");
        Skill last = skill;
        Skill next = skills.get(++index);
        totalPoints += last.getPoints();
        String cbLevelUp = ctx.getChatBot().levelUp(last.getTitle(), next.getTitle(), last.getPoints(), totalPoints);
        ctx.getInteractionInterface().send(new ChatItem(cbLevelUp));
        skill = next;
        save();
    }

    private void setLevel(String level) {
        if (level == null)
            return;
        skill = skills.get(Integer.parseInt(level));
        initialized = true;
    }
}