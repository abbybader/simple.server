package simple.server;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.servlet.GuiceFilter;

public class SimpleServer {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		
		context.addEventListener(new GuiceConfig());
		context.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
		context.addServlet(EmptyServlet.class, "/*");

		server.start();
	}
	
}
