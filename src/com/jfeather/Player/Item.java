package com.jfeather.Player;

public class Item {

	private String name, description;
	
	public Item(String itemName, String itemDescription) {
		name = itemName;
		description = itemDescription;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
}
