import java.util.*;


public class CommandLine {
	static Random random = new Random();

    public static void main(String[] argv) {
		Scanner sc = new Scanner(System.in);
		String ans = "";

        System.out.println(LE.HELLO[
        		random.nextInt(LE.HELLO.length)]);

		while (!ans.equals("quit")) {
			ArrayList<Problem> mission = new ArrayList<Problem>();
			mission.add(new SimpleAddition(10));
			mission.add(new SimpleAddition(10));
			mission.add(new SimpleAddition(10));
			mission.add(new SimpleAddition(10));
			mission.add(new SimpleAddition(10));

			mission.add(new SimpleAddition(100));
			mission.add(new SimpleAddition(100));
			mission.add(new SimpleAddition(100));
			mission.add(new SimpleAddition(100));
			mission.add(new SimpleAddition(100));

			mission.add(new SimpleSubtraction(10));
			mission.add(new SimpleSubtraction(10));
			mission.add(new SimpleSubtraction(10));
			mission.add(new SimpleSubtraction(10));
			mission.add(new SimpleSubtraction(10));

			mission.add(new SimpleAddition(100));
			mission.add(new SimpleAddition(100));
			mission.add(new SimpleAddition(100));
			mission.add(new SimpleAddition(100));
			mission.add(new SimpleAddition(100));

			for (Problem p : mission) {
		        if (!ans.equals("")) {
			        System.out.println(LE.ENCOURAGE[
			        		random.nextInt(LE.ENCOURAGE.length)]);
				}	

				System.out.println(p.getPrompt());
				ans = sc.next();
			    if (ans.equals(p.getAnswer())) {
			        System.out.println(LE.CORRECT[
			        		random.nextInt(LE.CORRECT.length)]);
			    } else if (!ans.equals("quit")) {
			        System.out.println(LE.SORRY[
			        		random.nextInt(LE.SORRY.length)]);
			    }
			}
		}
        System.out.println(LE.BYE[
        		random.nextInt(LE.BYE.length)]);

	}
}