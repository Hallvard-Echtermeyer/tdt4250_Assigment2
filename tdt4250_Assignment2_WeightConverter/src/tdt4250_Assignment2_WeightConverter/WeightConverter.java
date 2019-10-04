package tdt4250_Assignment2_WeightConverter;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import tdt4250_Assignment2_API.Ratio;
import tdt4250_Assignment2_API.Converter;
import tdt4250_Assignment2_API.ConverterImpl;

@Component(
		service=Converter.class,
		property = {
				ConverterImpl.CONVERTER_NAME_PROP + "=WeightConverter",
				ConverterImpl.CONVERTER_RESOURCE_PROP + "=tdt4250_Assignment2_WeightConverter#/tdt4250_Assignment2_WeightConverter/cc.txt"}
		)

public class WeightConverter extends ConverterImpl {
	
	



}
