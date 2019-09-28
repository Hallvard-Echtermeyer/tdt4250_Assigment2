package tdt4250_Assigment2_Util;

import java.util.HashMap;
import java.util.Map;

import tdt4250_Assigment2_Core.Ratio;



public class CurrencyConverter extends ConverterImpl {

	public final static String NAME = "Currency Converter";
	public final static Map<String, Map<String, Ratio>> RELATIONS = new HashMap<String, Map<String, Ratio>>();
	{
		RELATIONS.put("NOK", new HashMap<String, Ratio>(){{
			put("USD", new Ratio(0, 0.11061946902));
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
