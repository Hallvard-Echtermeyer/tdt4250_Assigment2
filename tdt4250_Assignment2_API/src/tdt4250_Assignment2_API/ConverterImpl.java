package tdt4250_Assignment2_API;

import java.util.HashMap;
import java.util.Map;

public abstract class ConverterImpl implements Converter{
	
	@Override
	public abstract String getConverterName();
	
	@Override
	public abstract  Map<String, Map<String, Ratio>> getConverterRelations();
	@Override
	public ConverterConversionResult convert(String currentUnit, String newUnit, int value) {
		// Ex. currentUnit = celsius, newUnit = fahrenheit, value = 0 => 32
		// Map => Find currentUnit
		// Map => Find fahrenheit, pull out value.
		if (getConverterRelations().containsKey(currentUnit)) {
			Map<String, Ratio> ratios = getConverterRelations().get(currentUnit);
			if (ratios.containsKey(newUnit)) {
				Ratio ratio = ratios.get(newUnit);
				double convertedValue = ratio.doConversion(value);
				return new ConverterConversionResult(true,
						"The conversion from " + value +" "+  currentUnit + " to " + newUnit + " equals: " + convertedValue,
						null);
			}
		}
		return new ConverterConversionResult(false, "The conversion from the unit " + currentUnit + ", to the unit "
				+ newUnit + ", could not be completed.", null);
	}

}
