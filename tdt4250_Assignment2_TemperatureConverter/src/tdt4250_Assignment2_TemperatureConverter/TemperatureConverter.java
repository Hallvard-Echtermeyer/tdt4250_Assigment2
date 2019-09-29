package tdt4250_Assignment2_TemperatureConverter;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import tdt4250_Assignment2_API.ConverterImpl;
import tdt4250_Assignment2_API.Ratio;

@Component
public class TemperatureConverter extends ConverterImpl {

	public final static String NAME = "Temperature Converter";
	public final static Map<String, Map<String, Ratio>> RELATIONS = new HashMap<String, Map<String, Ratio>>();
	{
		RELATIONS.put("C", new HashMap<String, Ratio>(){{
			put("F", new Ratio(32, 1.8));
		}});
	}
	
	@Override
	public String getConverterName() {
		return NAME;
	}
	
	@Override
	public  Map<String, Map<String, Ratio>> getConverterRelations() {
		return RELATIONS;
	}
}
