package simple.server.model;

import java.util.ArrayList;
import java.util.Arrays;
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
public class ListFactory {

	public static ToDoList buildListWith(String title, String... itemNames) {
		
		ListItem[] items = new ListItem[itemNames.length];
		for (int i = 0; i < items.length; i++) {
			items[i] = new ListItem(ListItem.counter++, itemNames[i], false);
		}
		return new ToDoList(title, new ArrayList<ListItem>(Arrays.asList(items)));
	}
	
	
}
