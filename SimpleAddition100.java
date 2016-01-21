import java.util.*;

interface Problem {
    public String getPrompt();
    public String getAnswer();
}

class SimpleAddition100Problem implements Problem {
    static final int MAX = 100;

    String prompt;
    String answer;
    
    public SimpleAddition100Problem() {
	int a = (int)Math.random() * MAX;
	int b = (int)Math.random() * MAX;
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

	Problem p = new SimpleAddition100Problem();
	System.out.println(p.getPrompt());

	String ans = null;
	try {  
	    ans = sc.next();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	while (!ans.equals("quit")) {
	    if (ans.equals(p.getAnswer())) {
	        System.out.println("Correct");
	    } else {
	        System.out.println("Sorry");
	    }
	    ans = sc.nextLine();
	}
	System.out.println("hello");
    }
}
