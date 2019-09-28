package tdt4250_Assigment2_Core;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class ConverterConversion {
	
	private static final String DEFAULT_MESSAGE = "Sorry, no matches";
	private Collection<Converter> converters = new ArrayList<Converter>();
	
	public void addConverter(Converter converter) {
		converters.add(converter);
	}

	public void removeConverter(Converter converter) {
		converters.remove(converter);
	}
	
	public ConverterConversion(Converter... converters) {
		this.converters.addAll(Arrays.asList(converters));
	}
	
	private ConverterConversionResult convert(String currentUnit, String newUnit, int value, Iterable<Converter> converters) {
		StringBuilder messages = new StringBuilder();
		URI link = null;
		boolean success = false;
		for (Converter converter : converters) {
			System.out.println("Converter: " + converter.getConverterName());
			ConverterConversionResult result = converter.convert(currentUnit,newUnit, value);
			if (result.isSuccess()) {
				messages.append(result.getMessage());
				messages.append(". Used converter: " + converter.getConverterName() + "\n");
				success = true;
				if (link == null) {
					link = result.getLink();
				}
			}
		}
		if (messages.length() == 0) {
			messages.append(DEFAULT_MESSAGE);
		}
		return new ConverterConversionResult(success, messages.toString(), link);
	}

	public ConverterConversionResult convert(String currentUnit, String newUnit, int value) {
		return convert(currentUnit, newUnit, value, converters);
	}

}


