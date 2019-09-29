package tdt4250_Assignment2_API;

public class Ratio {
	
	private String currentUnit;
	private String newUnit;
	private int term;
	private double factor;
	
	public Ratio(String currentUnit, String newUnit, int term, double factor) {
		this.term = term;
		this.factor = factor;
	}
	
	public double doConversion(int value) {
		double result = this.term + this.factor * value;
		return result;
	}

	public String getCurrentUnit() {
		return currentUnit;
	}

	public void setCurrentUnit(String currentUnit) {
		this.currentUnit = currentUnit;
	}

	public String getNewUnit() {
		return newUnit;
	}

	public void setNewUnit(String newUnit) {
		this.newUnit = newUnit;
	}

}
