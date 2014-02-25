package simple.server.model;

import javax.ws.rs.FormParam;
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
