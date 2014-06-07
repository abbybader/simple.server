package simple.server.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import simple.server.model.ListItem;
import simple.server.model.ToDoList;

@Path("list/{listName}/item")
@Produces("application/json")
public class ItemResource {

	private static final String LIST_NAME = "listName";

	//get
	@GET
	public ListItem getItem(@PathParam(LIST_NAME) String name, @QueryParam("id") long id) {
		ToDoList list = new ListResource().getList(name);
		List<ListItem> items = list.getItems();
		for (ListItem listItem : items) {
			if (listItem.getId() == id) {
				return listItem;
			}
		}
		throw new WebApplicationException(Response.status(Status.NOT_FOUND).entity("Could not item " + id + " in list " + name).build());
	}
	
	//put
	
	
	//post
	@POST
	public long addItem(@PathParam(LIST_NAME) String name, @FormParam("title") String title) {
		ToDoList list = new ListResource().getList(name);
		ListItem item = new ListItem(ListItem.counter++, title, false);
		list.addItem(item);
		return item.getId();
	}
	
	//delete
	

	private ListItem getItemById(String name, long id) {
		ToDoList list = new ListResource().getList(name);
		List<ListItem> items = list.getItems();
		for (ListItem listItem : items) {
			if (listItem.getId() == id) {
				return listItem;
			}
		}
		return null;
	}
}
