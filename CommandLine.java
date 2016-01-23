import java.util.*;


public class CommandLine {
	static Random random = new Random();

    public static void main(String[] argv) {
		Scanner sc = new Scanner(System.in);
		String command;

        System.out.println(LE.HELLO[
        		random.nextInt(LE.HELLO.length)]);

        int points = 0;
		ArrayList<Skill> mission = new ArrayList<Skill>();
		mission.add(new Skill(new Addition(10), 2, 100));
		mission.add(new Skill(new Subtraction(10), 2, 150));
		mission.add(new Skill(new Multiplication(12, 12), 3, 200));
		mission.add(new Skill(new Division(100, 10), 3, 250));
		mission.add(new Skill(new DivisionRemainders(100, 10), 4, 300));
		Iterator<Skill> path = mission.iterator();

		Skill skill = path.next();
		while (true) {
			System.out.println(skill.getPrompt());
			String response = sc.nextLine();

			if ("quit".equals(response)) {
		        System.out.println(LE.BYE[
		        		random.nextInt(LE.BYE.length)]);
		        return;
			}

			if (skill.checkAnswer(response)) {
		        System.out.println(LE.CORRECT[
		        		random.nextInt(LE.CORRECT.length)]);
		        int skillPoints = skill.completed();
		        if (skillPoints > 0) {
					System.out.println("Congratulations !!! you passed " + skill.getTitle());
		        	points += skillPoints;
		        	System.out.println("and earned " + skillPoints + " skill points");
		        	System.out.println("you now have " + points + " total points");
		        	skill = path.next();
					System.out.println("Now its time to practice " + skill.getTitle());
		        } else {
		        	skill.nextProblem();
		        }
			} else {
		        System.out.println(LE.SORRY[
		        		random.nextInt(LE.SORRY.length)]);				
			}
	        System.out.println(LE.ENCOURAGE[
			        		random.nextInt(LE.ENCOURAGE.length)]);
		}
	}
}