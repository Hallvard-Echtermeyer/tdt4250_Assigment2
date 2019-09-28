package tdt4250_Assigment2_Util;

import java.util.HashMap;
import java.util.Map;

import tdt4250_Assigment2_Core.Ratio;



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
