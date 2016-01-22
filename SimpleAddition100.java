import java.util.*;

interface Problem {
    public String getPrompt();
    public String getAnswer();
}

class SimpleAddition100 implements Problem {
    static final int MAX = 10000;

    String prompt;
    String answer;
//    static Random random = new Random();
    
    public SimpleAddition100() {
		int a = new Random().nextInt();
		int b = new Random().nextInt();;
		prompt = new String(a + " + " + b + " = ?");
		answer = new String(Integer.toString(a + b));
    }

    public String getPrompt() {
		return prompt;
    }

    public String getAnswer() {
		return answer;
    }

    public static void main(String[] argv) {
		Scanner sc = new Scanner(System.in);
		String ans = "";
		while (!ans.equals("quit")) {
			Problem p = new SimpleAddition100Problem();
			System.out.println(p.getPrompt());
			ans = sc.next();
		    if (ans.equals(p.getAnswer())) {
		        System.out.println("Correct");
		    } else if (!ans.equals("quit")) {
		        System.out.println("Sorry");
		    }
		}
	}
}
