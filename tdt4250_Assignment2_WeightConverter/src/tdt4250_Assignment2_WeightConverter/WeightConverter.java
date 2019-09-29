package tdt4250_Assignment2_WeightConverter;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import tdt4250_Assignment2_API.Ratio;
import tdt4250_Assignment2_API.ConverterImpl;


public class WeightConverter extends ConverterImpl {
	
	public final static String NAME = "Weight Converter";
	public final static Map<String, Map<String, Ratio>> RELATIONS = new HashMap<String, Map<String, Ratio>>();
	{
		RELATIONS.put("Kilo", new HashMap<String, Ratio>(){{
			put("Pounds", new Ratio("","",0, 2.204));
		}});
	}

	@Override
	public String getConverterName() {
		return NAME;
	}
	
	@Override
	public Map<String, Map<String, Ratio>> getConverterRelations() {
		return RELATIONS;
	}


}
