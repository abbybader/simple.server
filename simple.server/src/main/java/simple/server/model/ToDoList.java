package simple.server.model;

import java.util.List;
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
public class ToDoList {

	private String title;
	private List<ListItem> items;
	
	public ToDoList(String title, List<ListItem> items) {
		this.title = title;
		this.items = items;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<ListItem> getItems() {
		return items;
	}
	public void setItems(List<ListItem> items) {
		this.items = items;
	}

	public void addItem(ListItem item) {
		getItems().add(item);
	}
}
