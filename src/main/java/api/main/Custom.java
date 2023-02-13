package api.main;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import api.main.security.*;

public class Custom extends ResourceConfig 
{
	public Custom() 
	{
		packages("api.main");
		register(LoggingFilter.class);
	//	register(GsonMessageBodyHandler.class);
		register(SecurityFilter.class);
	}
}
