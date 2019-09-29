package tdt4250_Assignment2_API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

public class ResourceRatios {

	private Collection<Ratio> ratios = new ArrayList<Ratio>();

	public Collection<Ratio> read(InputStream input) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8"));
		String line = null;
		while ((line = reader.readLine()) != null) {
			// find indexes of commas and set
			addRatio(line);
		}
		
		return ratios;
	}

	public ResourceRatios()  {

	}

	public void addRatio(String line) {

		int cIndex = line.indexOf(',');
		String c = line.substring(0, cIndex);

		int nIndex = line.indexOf(',', cIndex + 1);
		String n = (String) line.subSequence(cIndex + 1, nIndex);

		int termIndex = line.indexOf(',', nIndex + 1);
		int term = Integer.parseInt((String) line.subSequence(nIndex + 1, termIndex));

		int factor = Integer.parseInt(line.substring(termIndex + 1));

		Ratio ratio = new Ratio(c, n, term, factor);
		ratios.add(ratio);
	}

}
