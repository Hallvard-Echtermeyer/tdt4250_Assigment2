package tdt4250_Assigment2_Core;

public class Ratio {
	
	private int term;
	private double factor;
	
	public Ratio(int term, double factor) {
		this.term = term;
		this.factor = factor;
	}
	
	public double doConversion(int value) {
		double result = this.term + this.factor * value;
		return result;
	}

}
