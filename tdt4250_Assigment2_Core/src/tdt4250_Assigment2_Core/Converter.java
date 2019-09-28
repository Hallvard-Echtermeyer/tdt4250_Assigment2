package tdt4250_Assigment2_Core;

import java.util.Map;

public interface Converter {
	
	String getConverterName();
	 Map<String, Map<String, Ratio>> getConverterRelations();
	ConverterConversionResult convert(String currentUnit, String newUnit,  int value);

}