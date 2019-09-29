package tdt4250_Assignment2_CurrencyConverter;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import tdt4250_Assignment2_API.ConverterImpl;
import tdt4250_Assignment2_API.Ratio;

public class CurrencyConverter extends ConverterImpl {

	public final static String NAME = "Currency Converter";
	public final static Map<String, Map<String, Ratio>> RELATIONS = new HashMap<String, Map<String, Ratio>>();
	{
		RELATIONS.put("NOK", new HashMap<String, Ratio>(){{
			put("USD", new Ratio("","",0, 0.11061946902));
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
