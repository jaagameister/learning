import java.util.*;

class Division extends Problem {

    public Division(int dividendMax, int divisorMax) {
        init(dividendMax, divisorMax);
        int quotientMax =  dividendMax / divisorMax;
        int b = new Random().nextInt(divisorMax) + 1;
        int a = b * new Random().nextInt(quotientMax);
        setPrompt(new String(a + " / " + b + " = ?"));
        setAnswer(new String(Integer.toString(a / b)));
    }

    public String getTitle() {
        return "Division with dividend to " + max1 + 
                " and divisor to " + max2;
    }


}
