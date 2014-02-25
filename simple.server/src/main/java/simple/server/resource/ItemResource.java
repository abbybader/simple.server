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
@Path("list/{listName}/item")
@Produces("application/json")
public class ItemResource {

	private static final String LIST_NAME = "listName";

	/**
	 * Add a new item to a list
	 * @param name the name of the list
	 * @param title the name of the list item
	 * @return the id of the new list item, unique in the list
	 */
	@POST
	public long addItem(@PathParam(LIST_NAME) String name, @FormParam("title") String title)  {		
		ListItem item = new ListItem(ListItem.counter++, title, false);
		ToDoList list = new ListResource().getList(name);
		list.addItem(item);
		return item.getId();
	}
	
	/**
	 * Update the title or "done" status of a list item
	 * @param name the name of the list
	 * @param id the id of the list item
	 * @param done the boolean "accomplished or not" status
	 * @param title the string title of the task
	 * @return the updated list item
	 */
	@PUT
	public ListItem updateItem(@PathParam(LIST_NAME) String name, @QueryParam("id") long id, @QueryParam("done") Boolean done, @QueryParam("title") String title) {
		ListItem result = getItem(name, id);
		if (result == null) {
			throw new WebApplicationException(Response.status(Status.NOT_FOUND).entity("Item " + id + " not found in list " + name).build());
		}
		if (title != null) { result.setTitle(title);}
		if (done != null) { result.setDone(done); }
		return result;
	}
	
	/**
	 * Return the item in the given list
	 * @param name the name of the list
	 * @param id the id of the item
	 * @return the list item
	 */
	@GET
	public ListItem getItem(@PathParam(LIST_NAME) String name, @QueryParam("id") long id) {
		ListItem result = getItemById(name, id);
		if (result == null) {
			throw new WebApplicationException(Response.status(Status.NOT_FOUND).entity("Item " + id + " not found in list " + name).build());
		}
		return result;
	}
	
	/**
	 * Delete an item from a list by id
	 * @param name the name of the list
	 * @param id the id of the item
	 */
	@DELETE
	public void deleteItem(@PathParam(LIST_NAME) String name, @QueryParam("id") long id) {
		ToDoList list = new ListResource().getList(name);
		List<ListItem> items = list.getItems();
		ListItem toRemove = new ListItem(id, "Mocked item - for deleting only", true);
		boolean success = items.remove(toRemove);
		if (!success) {
			throw new WebApplicationException(Response.status(Status.NOT_FOUND).entity("Could not delete item " + id + " from list " + name).build());
		}
	}

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
