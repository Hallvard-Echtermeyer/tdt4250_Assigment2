package tdt4250_Assignment2_API;

import java.net.URI;

public class ConverterConversionResult {
	
	private final boolean success;
	private final String message;
	private final URI link;
	
	public ConverterConversionResult(boolean success, String message, URI link) {
		super();
		this.success = success;
		this.message = message;
		this.link = link;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public URI getLink() {
		return link;
	}

}
