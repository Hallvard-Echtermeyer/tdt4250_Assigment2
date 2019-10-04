package tdt4250_Assignment2_CurrencyConverter;

import org.osgi.service.component.annotations.Component;

import tdt4250_Assignment2_API.Converter;
import tdt4250_Assignment2_API.ConverterImpl;

@Component(
		service=Converter.class,
		property = {
				ConverterImpl.CONVERTER_NAME_PROP + "=CurrencyConverter",
				ConverterImpl.CONVERTER_RESOURCE_PROP + "=tdt4250_Assignment2_CurrencyConverter#/tdt4250_Assignment2_CurrencyConverter/cc.txt"}
		)
public class CurrencyConverter extends ConverterImpl {

}
