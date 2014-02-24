package simple.server.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import simple.server.model.ListFactory;
import simple.server.model.ToDoList;


@Path("list/")
@Produces("application/json")
public class ListResource {
	
	@GET
	public List<String> getListNames() {
		return new ArrayList(lists.keySet());
	}
	
	@GET
	@Path("{listName}")
	public ToDoList getList(@PathParam("listName") String name) {
		
		ToDoList toDoList = lists.get(name);
		if (toDoList == null) {
			throw new WebApplicationException(Response.status(Status.NOT_FOUND).entity("List not found: " + name).build());
		}
		return toDoList;
	}
	
	private static Map<String, ToDoList> lists;
	
	static {
		lists = new HashMap<String, ToDoList>();
		lists.put("groceries",ListFactory.buildListWith("groceries", "eggs", "sugar", "milk","bread","celery","cereal"));
		lists.put("Build a birdhouse", ListFactory.buildListWith("Build a birdhouse", "Research designs","Finalize design choice","Draw up materials list","Buy materials from hardware store","Build it!"));
		lists.put("Weekend", ListFactory.buildListWith("Weekend", "Clean house", "Grocery shopping", "Laundry", "Cook lunches for the week","Football"));
	}
	
	
}
