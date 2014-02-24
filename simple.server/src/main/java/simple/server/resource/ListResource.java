package simple.server.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import simple.server.model.ListFactory;
import simple.server.model.ListItem;
import simple.server.model.ToDoList;


@Path("list/")
@Produces("application/json")
public class ListResource {
    
    private MockDatabase dataMapper;
    
    public ListResource() {
        dataMapper = new MockDatabase();
    }
	
	@GET
	public List<String> getListNames() {
		return dataMapper.getListNames();
	}
	
	@GET
	@Path("{listName}")
	public ToDoList getList(@PathParam("listName") String name) {
		
		ToDoList toDoList = dataMapper.getList(name);
		if (toDoList == null) {
			throw new WebApplicationException(Response.status(Status.NOT_FOUND).entity("List not found: " + name).build());
		}
		return toDoList;
	}
	
	@POST
    @Path("{listName}")
	public void addList(@PathParam("listName") String name) {
	    if (dataMapper.contains(name)) {
	        throw new WebApplicationException(Response.status(Status.CONFLICT).entity("List " + name + " already exists").build());
	    }
	    ToDoList list = new ToDoList(name, new ArrayList<ListItem>());
	    dataMapper.putList(name, list);
	}
	
	@DELETE
    @Path("{listName}")
	public void deleteList(@PathParam("listName") String name) {
	    ToDoList removedList = dataMapper.deleteList(name);
	    if (removedList == null) {
            throw new WebApplicationException(Response.status(Status.NOT_FOUND).entity("Could not delete list " + name ).build());
        }
	}
	
	/**
	 * Mock of an actual persistence layer.  In real life, this could be a service class talking to 
	 * an actual database.
	 */
	private static class MockDatabase {

	    private static Map<String, ToDoList> lists;

	    static {
	        lists = new HashMap<String, ToDoList>();
	        lists.put("groceries",ListFactory.buildListWith("groceries", "eggs", "sugar", "milk","bread","celery","cereal"));
	        lists.put("Build a birdhouse", ListFactory.buildListWith("Build a birdhouse", "Research designs","Finalize design choice","Draw up materials list","Buy materials from hardware store","Build it!"));
	        lists.put("Weekend", ListFactory.buildListWith("Weekend", "Clean house", "Grocery shopping", "Laundry", "Cook lunches for the week","Football"));
	    }
	    public boolean contains(String name) {
	        return lists.containsKey(name);
	    }
	    public void putList(String name, ToDoList list) {
	        lists.put(name, list);
	    }
	    public ToDoList getList(String name) {
	        return lists.get(name);
	    }
	    public ToDoList deleteList(String name) {
	        return lists.remove(name);
	    }
	    public List<String> getListNames() {
	        return new ArrayList<String>(lists.keySet());
	    }
	}
	
	
}