package simple.server;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.servlet.GuiceFilter;

public class SimpleServer {

	/**
	 * Simple backend for a To Do app
	 * 
	 * @param args
	 *            { httpPort }
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		int port = getPort(args);
		Server server = new Server(port);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");

		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setDirectoriesListed(true);
		resourceHandler.setResourceBase("webapp");
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { resourceHandler, context });
		server.setHandler(handlers);

		context.addEventListener(new GuiceConfig());
		context.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
		context.addServlet(EmptyServlet.class, "/*");

		server.start();
	}

	private static int getPort(String[] args) {
		return args.length > 0 ? Integer.valueOf(args[0]) : 1500;
	}

}
