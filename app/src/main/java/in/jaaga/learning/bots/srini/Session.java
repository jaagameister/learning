package in.jaaga.learning.bots.srini;


public class Session {
	Skill skill;
    String state;
    String name = null;
    int points = 0;
    String password = "Password";
    static String device = "Android";

    public Session() {
    }

    public static void setDevice(String input){
        device = input;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Skill getSkill() {
		return skill;
	}

    public String getName() {
        return name;
    }

    public  void setName(String name){
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }
}