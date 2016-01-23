import java.util.*;

public class VariableAddition extends Problem {

    public VariableAddition(int max) {
		init(max);
		int a = new Random().nextInt(max);
		int b = new Random().nextInt(max);
		if (new Random().nextInt(2) % 2 == 0) {
			setPrompt(new String(a + " + ? = " + (a+b)));
		} else {
			setPrompt(new String("? + " + a + " = " + (a+b)));
    	}
		setAnswer(new String(Integer.toString(b)));
    }

    public VariableAddition(int max1, int max2) {
    	this(max1);
    }

    public String getTitle() {
    	return "Addition with variables upto " + max1;
    }
}
