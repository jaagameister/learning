import java.util.*;

class Subtraction extends Problem {

    public Subtraction(int max) {
    	init(max);

        int a,b;
        if (max > 0) {
        	a = new Random().nextInt(max);
    		b = new Random().nextInt(a + 1);
        } else {  // max is negative
            a = new Random().nextInt(max * -2) + max;
            b = new Random().nextInt(max * -2) + max;
        }       
		setPrompt(new String(a + " - " + b + " = ?"));
		setAnswer(new String(Integer.toString(a - b)));
    }

    public Subtraction(int max1, int max2) {
        this(max1);
    }

    public String getTitle() {
        int m = Math.abs(max1);
        String title = "Subtraction to " + max1; 
        if (max1 > 0)
            return title;
        return title + " with negative numbers";
    }


}
