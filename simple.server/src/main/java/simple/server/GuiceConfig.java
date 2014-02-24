package simple.server;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import simple.server.resource.ItemResource;
import simple.server.resource.ListResource;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletModule() {

			@Override
			protected void configureServlets() {
				bind(ListResource.class);
				bind(ItemResource.class);
				
				bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
				bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);				
				
				Map<String, String> initParams = new HashMap<String, String>();
				initParams.put("com.sun.jersey.config.feature.Trace", "true");
				initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
				initParams.put("com.sun.jersey.config.property.packages","com.wordnik.swagger.jersey.listing");
				serve("*").with(GuiceContainer.class, initParams);
			};
		});
	}
}
