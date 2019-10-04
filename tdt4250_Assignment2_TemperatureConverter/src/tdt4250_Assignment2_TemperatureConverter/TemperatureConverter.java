package tdt4250_Assignment2_TemperatureConverter;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import tdt4250_Assignment2_API.Converter;
import tdt4250_Assignment2_API.ConverterImpl;
import tdt4250_Assignment2_API.Ratio;

@Component(
		service=Converter.class,
		property = {
				ConverterImpl.CONVERTER_NAME_PROP + "=TemperatureConverter",
				ConverterImpl.CONVERTER_RESOURCE_PROP + "=tdt4250_Assignment2_TemperatureConverter#/tdt4250_Assignment2_TemperatureConverter/cc.txt"}
		)

public class TemperatureConverter extends ConverterImpl {
}
