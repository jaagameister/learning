package in.jaaga.learning.cli;

import java.util.*;

class Multiplication extends Problem {
    
    public Multiplication(int factor1, int factor2) {
    	init(factor1, factor2);

		int a = new Random().nextInt(Math.abs(max1));
		int b = new Random().nextInt(Math.abs(max2));
    	if (max1 < 0) {
			a -= Math.abs(max1/2);
			b -= Math.abs(max2/2);			
		}
		setPrompt(new String(a + " * " + b + " = ?"));
		setAnswer(new String(Integer.toString(a * b)));
    }

    public String getTitle() {
        int m = Math.abs(max1);
        String title = "Multiplication to " + max1; 
        if (max1 > 0)
            return title;
        return title + " with negative numbers";
    }
}
