package simple.server.model;

import java.util.ArrayList;
import java.util.Arrays;

public class ListFactory {

	public static ToDoList buildListWith(String title, String... itemNames) {
		
		ListItem[] items = new ListItem[itemNames.length];
		for (int i = 0; i < items.length; i++) {
			items[i] = new ListItem(ListItem.counter++, itemNames[i], false);
		}
		return new ToDoList(title, new ArrayList<ListItem>(Arrays.asList(items)));
	}
	
	
}
