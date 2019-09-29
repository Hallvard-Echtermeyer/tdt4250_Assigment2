package tdt4250_Assignment2_CurrencyConverter;

import org.osgi.service.component.annotations.Component;
import tdt4250_Assignment2_API.ConverterImpl;

@Component(
		property = {
				ConverterImpl.CONVERTER_NAME_PROP + "=CurrencyConverter",
				ConverterImpl.CONVERTER_RESOURCE_PROP + "=NOK,USD,0,10"}
		)
public class CurrencyConverter extends ConverterImpl {

}
