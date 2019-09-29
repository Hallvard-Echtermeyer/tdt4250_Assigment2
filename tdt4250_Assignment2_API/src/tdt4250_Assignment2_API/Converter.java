package tdt4250_Assignment2_API;

import java.util.Map;
import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface Converter {
	
	String getConverterName();
	 Map<String, Map<String, Ratio>> getConverterRelations();
	ConverterConversionResult convert(String currentUnit, String newUnit,  int value);

}