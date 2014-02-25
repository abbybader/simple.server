package simple.server;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.servlet.GuiceFilter;

/*
 * To Do App: a coding exercise on: ExtJS, REST, and Javascript.
 * Copyright (C) 2014  Spectraseis, Inc
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * @author Abby Bader
 *
 */
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
        resourceHandler.setDirectoriesListed(false);
        resourceHandler.setResourceBase("webapp");

        ResourceHandler docsHandler = new ResourceHandler();
        docsHandler.setDirectoriesListed(true);
        docsHandler.setResourceBase("target/dist/docs");
        ContextHandler docsContext = new ContextHandler("/docs");
        docsContext.setHandler(docsHandler);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resourceHandler, docsContext, context });
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
