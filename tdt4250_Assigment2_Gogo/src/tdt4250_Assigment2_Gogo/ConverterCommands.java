package tdt4250_Assigment2_Gogo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.felix.service.command.Descriptor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;

/*import tdt4250.dict3.api.Dict;
import tdt4250.dict3.api.DictSearchResult;
import tdt4250.dict3.util.MutableWords;
import tdt4250.dict3.util.ResourceWords;
import tdt4250.dict3.util.SortedSetWords;*/

// see https://enroute.osgi.org/FAQ/500-gogo.html

@Component(
		service = ConverterCommands.class,
		property = {
			"osgi.command.scope=converters",
			"osgi.command.function=list",
			"osgi.command.function=add",
			"osgi.command.function=remove"
		}
	)

public class ConverterCommands {
	
	
	@Descriptor("Lists all the available conversiones which can be used")
	public void list() {
		System.out.println("hello world we got this far");
	}
	
	@Descriptor("Add another ")
	public void add() {
		System.out.println("....");
	}
	
	
	


}




