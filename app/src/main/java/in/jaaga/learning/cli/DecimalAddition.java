package in.jaaga.learning.cli;

import java.util.*;
import java.math.*;

public class DecimalAddition extends Problem {

	int max, min, digitsNumber;


    public DecimalAddition (int max, int min, int digitsNumber) {
		this.max = max;
		this.min = min;
		this.digitsNumber = digitsNumber;
		BigDecimal a = generateRandomBigDecimal();
		a = a.setScale(digitsNumber, RoundingMode.CEILING);
		BigDecimal b = generateRandomBigDecimal();
		b = b.setScale(digitsNumber, RoundingMode.CEILING);
		BigDecimal c = new BigDecimal(0).add(a).add(b);
		c = c.setScale(digitsNumber, RoundingMode.CEILING);
		setPrompt(a + " + " + b + " = ?");
		setAnswer(String.valueOf(c));
    }

	public BigDecimal generateRandomBigDecimal(){
		int range = (max - min);
		BigDecimal randomNumber = new BigDecimal((Math.random() * range) + min);
		return randomNumber;
	}

	public Problem next() {
		return new DecimalAddition(max, min, digitsNumber);
	}

    public String getTitle() {
    	return "Decimal addition with variables upto " + max1;
    }
}
