import java.util.*;

class Division extends Problem {
    int dividendMax, divisorMax;

    public Division(int dividendMax, int divisorMax) {
        this.divisorMax = divisorMax;
        this.dividendMax = dividendMax;
        int quotientMax =  dividendMax / divisorMax;
        int b = new Random().nextInt(divisorMax) + 1;
        int a = b * new Random().nextInt(quotientMax);
        setPrompt(new String(a + " / " + b + " = ?"));
        setAnswer(new String(Integer.toString(a / b)));
    }

    public Problem next() {
        return new Division(dividendMax, divisorMax);
    }

    public String getTitle() {
        return "Division with dividend to " + dividendMax + 
                " and divisor to " + divisorMax;
    }


}
