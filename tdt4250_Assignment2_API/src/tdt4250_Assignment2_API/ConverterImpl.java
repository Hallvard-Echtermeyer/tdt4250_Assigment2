package tdt4250_Assignment2_API;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

@Component(configurationPid = ConverterImpl.FACTORY_PID, configurationPolicy = ConfigurationPolicy.REQUIRE)

public class ConverterImpl implements Converter {

	public static final String FACTORY_PID = "tdt4250_Assignment2_API.ConverterImpl";
	
	public static final String CONVERTER_RATIOS_PROP = "converterRatios";
	public static final String CONVERTER_RESOURCE_PROP = "converterResource";
	public static final String CONVERTER_NAME_PROP = "converterName";

	private ResourceRatios resourceRatios = new ResourceRatios();
	private String name;
	private Map<String, Map<String, Ratio>> relations = new HashMap<String, Map<String, Ratio>>();

	@Override
	public String getConverterName() {
		return name;
	}

	protected void setConverterName(String name) {
		this.name = name;
	}

	@Override
	public Map<String, Map<String, Ratio>> getConverterRelations() {
		return relations;
	}

	public @interface ConverterImplConfig {
		String converterName();

		String converterResource() default "";

		String[] converterRatios() default {};
	}

	@Activate
	public void activate(BundleContext bc, ConverterImplConfig config) {
		update(bc, config);
	}

	@Modified
	public void modify(BundleContext bc, ConverterImplConfig config) {
		update(bc, config);
	}

	protected void update(BundleContext bc, ConverterImplConfig config) {
		setConverterName(config.converterName());
		System.out.println("Converter name from config: " + config.converterName());
		System.out.println("Converter resource from config: " + config.converterResource());
		System.out.println(Arrays.toString(config.converterRatios()));
		String converterUrl = config.converterResource();
		if (converterUrl.length() > 0) {
			URL url = null;
			try {
				url = new URL(converterUrl);
			} catch (MalformedURLException e) {
				// try bundle resource format: <bundle-id>#<resource-path>
				int pos = converterUrl.indexOf('#');
				String bundleId = converterUrl.substring(0, pos);
				String resourcePath = converterUrl.substring(pos + 1);
				for (Bundle bundle : bc.getBundles()) {
					if (bundle.getSymbolicName().equals(bundleId)) {
						url = bundle.getResource(resourcePath);
					}
				}
			}
			try {
				System.out.println("Loading ratios from " + url);
				Collection<Ratio> newRatios = resourceRatios.read(url.openStream());
				for (Ratio ratio : newRatios) {
					Map<String, Ratio> ratios;
					if (!relations.containsKey(ratio.getCurrentUnit())) {
						relations.put(ratio.getCurrentUnit(), new HashMap<String, Ratio>());
					}
					ratios = relations.get(ratio.getCurrentUnit());
					ratios.put(ratio.getNewUnit(), ratio);
				}
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		System.out.println("After url loading");
		System.out.println(relations);
		if (config.converterRatios().length > 0) {
			String[] ss = config.converterRatios();
			if (relations == null) {
				relations = new HashMap<String, Map<String, Ratio>>();
				
			}

			for (int i = 0; i < ss.length; i++) {
				resourceRatios.addRatio(ss[i]);
			}
			Collection<Ratio> ratios = resourceRatios.getRatios();
			for(Ratio ratio : ratios) {
				if(!relations.containsKey(ratio.getCurrentUnit())) {
					relations.put(ratio.getCurrentUnit(), new HashMap<String, Ratio>());
				}
				Map<String, Ratio> map = relations.get(ratio.getCurrentUnit());
				map.put(ratio.getNewUnit(), ratio);
				
			}
		}
		System.out.println("After ss..");
		System.out.println(relations);
	}

	@Override
	public ConverterConversionResult convert(String currentUnit, String newUnit, int value) {
		// Ex. currentUnit = celsius, newUnit = fahrenheit, value = 0 => 32
		// Map => Find currentUnit
		// Map => Find fahrenheit, pull out value.
		if (getConverterRelations().containsKey(currentUnit)) {
			Map<String, Ratio> ratios = getConverterRelations().get(currentUnit);
			if (ratios.containsKey(newUnit)) {
				Ratio ratio = ratios.get(newUnit);
				double convertedValue = ratio.doConversion(value);
				return new ConverterConversionResult(true, "The conversion from " + value + " " + currentUnit + " to "
						+ newUnit + " equals: " + convertedValue, null);
			}
		}
		return new ConverterConversionResult(false, "The conversion from the unit " + currentUnit + ", to the unit "
				+ newUnit + ", could not be completed.", null);
	}

}
