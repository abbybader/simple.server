package simple.server.model;

import javax.ws.rs.FormParam;

public class ListItem {


	public static long counter = 0;
	
	@FormParam("id") 
	private long id;
	@FormParam("title") 
	private String title;
	@FormParam("done") 
	private boolean done;
	
	public ListItem(long id, String title, boolean done) {
		this.id = id;
		this.title = title;
		this.done = done;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	@Override
	public int hashCode() {
		return Long.valueOf(id).hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if ( ! (obj instanceof ListItem ) ) { return false; }
		ListItem other = (ListItem) obj;
		return (id == other.id);
	}
	
}
