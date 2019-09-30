package tdt4250_Assignment2_Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import org.osgi.service.component.annotations.*;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletPattern;

import tdt4250_Assignment2_API.Converter;
import tdt4250_Assignment2_API.ConverterConversion;
import tdt4250_Assignment2_API.ConverterConversionResult;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;


@Component
@HttpWhiteboardServletPattern("/converter/*")
public class ConverterServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;
	
	private ConverterConversion converterConversion = new ConverterConversion();

	@Reference(
			cardinality = ReferenceCardinality.MULTIPLE,
			policy = ReferencePolicy.DYNAMIC,
			bind = "addConverter",
			unbind = "removeConverter"
	)
	public void addConverter(Converter converter) {
		converterConversion.addConverter(converter);
	}
	public void removeConverter(Converter converter) {
		converterConversion.removeConverter(converter);
	}
	
	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	private volatile LoggerFactory loggerFactory;
	
	private Logger getLogger() {
		if (loggerFactory != null) {
			return loggerFactory.getLogger(ConverterServlet.class);
		}
		return null;
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<String> segments = new ArrayList<>();
		String path = request.getPathTranslated();
		Logger logger = getLogger();
		logger.info("Received request for " + path);
		if (path != null) {
			segments.addAll(Arrays.asList(path.split("\\/")));
		}
		if (segments.size() > 0 && segments.get(0).length() == 0) {
			segments.remove(0);
		}
		if (segments.size() > 1) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Request must contain max 1 path segment");
			return;
		}
		System.out.println("Got past segment stuff");
		String c = request.getParameter("c");
		System.out.println("c: " + c);
		String n = request.getParameter("n");
		System.out.println("n: " + n);
		System.out.println("Got past string arguments");
		System.out.println(request.getParameter("v"));
		int v = Integer.parseInt(request.getParameter("v"));
		System.out.println("Got past int arguments");
		ConverterConversionResult result = converterConversion.convert(c,n,v);
		response.setContentType("text/plain");
		PrintWriter writer = response.getWriter();
		if (result.getLink() != null) {
			writer.print(result.getLink());
		}
		writer.print(result.getMessage());
	}

}