package tdt4250_Assigment2_Gogo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import tdt4250_Assigment2_Core.Converter;



public class Activator implements BundleActivator {

	private static Activator SINGLETON = null;
	
	static Activator getActivator() {
		return SINGLETON;
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		SINGLETON = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		SINGLETON = null;
	}
	
	private Map<String, ServiceRegistration<Converter>> serviceRegistrations = new HashMap<String, ServiceRegistration<Converter>>();

	public boolean isManual(String dictName) {
		return serviceRegistrations.containsKey(dictName);
	}
	
	public void test() {
		System.out.println("yes we made it");
	}

	public Collection<String> getAllConverterNames() {
		System.out.println("we got into getallnames");
		Collection<String> allNames = new ArrayList<>();
		// iterate through all Converter  objects
		BundleContext bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		try {
			for (ServiceReference<Converter> serviceReference : bc.getServiceReferences(Converter.class, null)) {
				Converter conv = bc.getService(serviceReference);
				System.out.println(conv.getConverterName());
				try {
					allNames.add(conv.getConverterName());
				} finally {
					bc.ungetService(serviceReference);
				}
			}
		} catch (InvalidSyntaxException e) {
		}
		return allNames;
	}
	
}
