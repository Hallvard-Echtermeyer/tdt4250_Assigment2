package tdt4250_Assignment2_API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;


public class ResourceRatios {

	private Collection<Ratio> ratios = new ArrayList<Ratio>();

	public Collection<Ratio> read(InputStream input) throws IOException {
		System.out.println("Started reading from url");
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8"));
		String line = reader.readLine();
		while (line != null) {
			// find indexes of commas and set
			System.out.println("Added line: " + line.toString());
			addRatio(line);
			System.out.println("About to read line");
			line = reader.readLine();
			System.out.println("Next line is: " + line);
		}
		
		return ratios;
	}

	public ResourceRatios()  {

	}
	
	public Collection<Ratio> getRatios(){
		return ratios;
	}

	public void addRatio(String line) {
		System.out.println("Starting to add ratio");

		int cIndex = line.indexOf(',');
		String c = line.substring(0, cIndex);
		System.out.println(c);
		int nIndex = line.indexOf(',', cIndex + 1);
		String n = (String) line.subSequence(cIndex + 1, nIndex);
		System.out.println(n);
		int termIndex = line.indexOf(',', nIndex + 1);
		int term = Integer.parseInt((String) line.subSequence(nIndex + 1, termIndex));
		System.out.println(term);
		double factor = Double.parseDouble(line.substring(termIndex + 1));
		System.out.println(factor);
		Ratio ratio = new Ratio(c, n, term, factor);
		System.out.println("Created ratio");
		ratios.add(ratio);
		System.out.println("Added ratio");
	}
	
	public static void main(String[] args) {
		ResourceRatios rs = new ResourceRatios();
		System.out.println(rs.ratios);
		rs.addRatio("F,V,10,30");
		System.out.println(rs.ratios);
		Ratio ratio = rs.ratios.iterator().next();
	
	}

}
