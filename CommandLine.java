import java.util.*;


public class CommandLine {
	static Random random = new Random();

    public static void main(String[] argv) {
		Scanner sc = new Scanner(System.in);
		String command;

        System.out.println(LE.HELLO[
        		random.nextInt(LE.HELLO.length)]);

		ArrayList<Problem> mission = new ArrayList<Problem>();
		mission.add(new SimpleAddition(10));
		mission.add(new SimpleAddition(20));
		mission.add(new SimpleAddition(30));
		mission.add(new SimpleAddition(40));
		Iterator<Problem> path = mission.iterator();

		Problem problem = path.next();
		int repsRequired = 5;
		int rep = 0;
		while (true) {
			System.out.println(problem.getPrompt());
			String response = sc.next();

			if ("quit".equals(response)) {
		        System.out.println(LE.BYE[
		        		random.nextInt(LE.BYE.length)]);
		        return;
			}

			if (problem.checkAnswer(response)) {
		        System.out.println(LE.CORRECT[
		        		random.nextInt(LE.CORRECT.length)]);
				rep++;
				if (rep < repsRequired) {
					problem = problem.next();
				} else {
					problem = path.next();
					repsRequired = 5;
					rep = 0;
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