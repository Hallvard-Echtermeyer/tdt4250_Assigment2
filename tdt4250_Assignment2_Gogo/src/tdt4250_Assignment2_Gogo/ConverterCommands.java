package tdt4250_Assignment2_Gogo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.felix.service.command.Descriptor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import tdt4250_Assignment2_API.Converter;
import tdt4250_Assignment2_API.ConverterConversionResult;
import tdt4250_Assignment2_API.ConverterImpl;

// see https://enroute.osgi.org/FAQ/500-gogo.html

@Component(
		service = ConverterCommands.class,
		property = {
			"osgi.command.scope=converter",
			"osgi.command.function=list",
			"osgi.command.function=convert",
			"osgi.command.function=add",
			"osgi.command.function=remove"
		}
	)
public class ConverterCommands {

	private Configuration getConfig(String converterName) {
		try {
			Configuration[] configs = cm.listConfigurations("(&(" + ConverterImpl.CONVERTER_NAME_PROP+ "=" + converterName + ")(service.factoryPid=" + ConverterImpl.FACTORY_PID + "))");
			if (configs != null && configs.length >= 1) {
				return configs[0];
			}
		} catch (IOException | InvalidSyntaxException e) {
		}
		return null;
	}

	@Descriptor("list available converters")
	public void list() {
	
		BundleContext bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		try {
			for (ServiceReference<Converter> serviceReference : bc.getServiceReferences(Converter.class, null)) {
				Converter converter = bc.getService(serviceReference);
				try {
					if (converter != null) {
					
						if (getConfig(converter.getConverterName()) != null) {
													
						}
					}
				} finally {
					bc.ungetService(serviceReference);
				}
				
			}
		} catch (InvalidSyntaxException e) {
		}
	
	}

	@Descriptor("convert the value according to some available converter")
	public void convert(
			@Descriptor("Current unit")
			String c,
			@Descriptor("New unit")
			String n,
			@Descriptor("Value to be converted")
			int v
			) {
		BundleContext bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		try {
			// iterate through all Dict service objects
			for (ServiceReference<Converter> serviceReference : bc.getServiceReferences(Converter.class, null)) {
				Converter converter = bc.getService(serviceReference);
				if (converter != null) {
					try {
						ConverterConversionResult result = converter.convert(c,n,v);
						
					} finally {
						bc.ungetService(serviceReference);
					}
				} else {

				}
			}
		} catch (InvalidSyntaxException e) {
		}
	}

	@Reference(cardinality = ReferenceCardinality.MANDATORY)
	private ConfigurationAdmin cm;

	@Descriptor("add a Converter, with content from a URL and/or specific Ratios")
	public void add(
			@Descriptor("the name of the new converter")
			String name,
			@Descriptor("the URL of file with the ratios, or a single ratio to add to the converter")
			String urlStringOrRatio,
			@Descriptor("additional ratios to add to the converter")
			String... rr
			) throws IOException, InvalidSyntaxException {
		URL url = null;
		Collection<String> ratios = new ArrayList<String>();
		try {
			url = new URL(urlStringOrRatio);
		} catch (MalformedURLException e) {
			ratios.add(urlStringOrRatio);
		}
		ratios.addAll(Arrays.asList(rr));
		String actionName = "updated";
		// lookup existing configuration
		Configuration config = getConfig(name);
		
		if (config == null) {
			// create a new one
			config = cm.createFactoryConfiguration(ConverterImpl.FACTORY_PID, "?");
			actionName = "added";
		}
		Dictionary<String, String> props = new Hashtable<>();
		props.put(ConverterImpl.CONVERTER_NAME_PROP, name);
		if (url != null) {
			props.put(ConverterImpl.CONVERTER_RESOURCE_PROP, url.toString());
		}
		if (ratios != null && ratios.size() > 0) {
			props.put(ConverterImpl.CONVERTER_RATIOS_PROP, String.join(" ", ratios));
		}
	
		config.update(props);
		
	}

	@Descriptor("remove a (manually added) converter")
	public void remove(
			@Descriptor("the name of the (manually added) dictionary to remove")
			String name
			) throws IOException, InvalidSyntaxException {
		Configuration config = getConfig(name);
		boolean removed = false;
		if (config != null) {
			config.delete();
			removed = true;
		}
		
	}
}
