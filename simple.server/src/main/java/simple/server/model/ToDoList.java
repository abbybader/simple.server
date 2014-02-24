package simple.server.model;

import java.util.List;

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
