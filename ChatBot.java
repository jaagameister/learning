import java.util.Random;

public class ChatBot {

	static Random random = new Random();

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

	Skill skill;

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public String getEncouragement() {
		if (skill.getRemaining() == 1)
			return "just " + skill.getRemaining() + " left in this skill";
		return ENCOURAGE[random.nextInt(LE.ENCOURAGE.length)];
	}

}