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
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8"));
		String line = reader.readLine();
		while (line != null) {
			// find indexes of commas and set
			
			addRatio(line);
			
			line = reader.readLine();
			
		}
		
		return ratios;
	}

	public ResourceRatios()  {

	}
	
	public Collection<Ratio> getRatios(){
		return ratios;
	}

	public void addRatio(String line) {
		
		int cIndex = line.indexOf(',');
		String c = line.substring(0, cIndex);
		
		int nIndex = line.indexOf(',', cIndex + 1);
		String n = (String) line.subSequence(cIndex + 1, nIndex);
		
		int termIndex = line.indexOf(',', nIndex + 1);
		int term = Integer.parseInt((String) line.subSequence(nIndex + 1, termIndex));
		
		double factor = Double.parseDouble(line.substring(termIndex + 1));
		
		Ratio ratio = new Ratio(c, n, term, factor);
		
		ratios.add(ratio);
		
	}
	
	public static void main(String[] args) {
		ResourceRatios rs = new ResourceRatios();
		
		rs.addRatio("F,V,10,30");
		
		Ratio ratio = rs.ratios.iterator().next();
	
	}

}
