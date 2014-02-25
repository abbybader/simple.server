package simple.server.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import simple.server.model.ListFactory;
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
	    ToDoList list = new ToDoList(name, new ArrayList<ListItem>());
	    boolean success = dataMapper.putList(name, list);
	    if (!success) {
	        throw new WebApplicationException(Response.status(Status.CONFLICT).entity("List " + name + " already exists").build());
	    }
	}
	
	/**
	 * Update the items in a list.  Given a JSON payload that is a list of item ids, update the list so that it contains the items with those ids, in the given order. 
	 * @param idsInOrder A list of long ids, auto-constructed from the payload
	 * @param name The name of the list
	 * @return Returns the updated list
	 */
	@PUT
	@Consumes("application/json")
	@Path("{listName}")
	public ToDoList updateList(List<Long> idsInOrder, @PathParam("listName") String name) {
	    ToDoList list = dataMapper.getList(name);
	    if (list == null) {
	        throw new WebApplicationException(Response.status(Status.NOT_FOUND).entity("List not found: " + name).build());
	    }
	    List<ListItem> itemsInNewOrder = new ArrayList<ListItem>();
	    ItemResource ir = new ItemResource();
	    for (Long id : idsInOrder) {
            itemsInNewOrder.add(ir.getItem(name, id));
        }
	    list.setItems(itemsInNewOrder);
	    return list;
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

	    private static ConcurrentMap<String, ToDoList> lists;

	    static {
	        lists = new ConcurrentHashMap<String, ToDoList>();
	        lists.put("groceries",ListFactory.buildListWith("groceries", "eggs", "sugar", "milk","bread","celery","cereal"));
	        lists.put("Build a birdhouse", ListFactory.buildListWith("Build a birdhouse", "Research designs","Finalize design choice","Draw up materials list","Buy materials from hardware store","Build it!"));
	        lists.put("Weekend", ListFactory.buildListWith("Weekend", "Clean house", "Grocery shopping", "Laundry", "Cook lunches for the week","Football"));
	    }
	    public boolean putList(String name, ToDoList list) {
	         return lists.putIfAbsent(name, list) == null;
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
