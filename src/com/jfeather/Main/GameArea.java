package com.jfeather.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jfeather.Player.Item;

public class GameArea {

	/*
	 * This is the class that will define an "area" the player can be in at any given point in the game
	 * This class will have the following attributes:
	 * Items - a list of items that can be picked up while the player is in the area
	 * 		 - these are removed from the list when the player takes them, so they cannot be taken again
	 * 		 - these can also be added the level when the player drops something
	 * Entrances/Exits - the level will be accessible through any combination of the cardinal directions
	 * People - these are NPC's that the player game interact with when in the same area as them
	 */
	
	private NPC npc;
	private ArrayList<Item> items;
	private String description;
	
	// If any of these values are set to null, the program will assume that they don't exist in this level
	public GameArea(String levelDescription, NPC levelNPC, ArrayList<Item> levelItems) {
		npc = levelNPC;
		items = levelItems;
		description = levelDescription;
		items = new ArrayList<>();
	}
	
	public String getDescription() {
		String finalDescr = "";
		if (items.size() < 1)
			finalDescr = description;
		if (items.size() == 1)
			finalDescr = description + "\nYou see a " + items.get(0).getName() + " on the ground!";
		if (items.size() > 1) {
			String descr = "You see several items on the ground:";
			for (Item i: items)
				descr += "\nA " + i.getName();
		}
		if (npc != null) {
			String[][] introPhrases = {{"You see a ", " hiding in the shadows."}, {"You spot a ", "."}};
			int index;
			Random rng = new Random();
			finalDescr += "\n" + introPhrases[(index = rng.nextInt(introPhrases.length))][0] + npc.getDescription() + introPhrases[index][0];
		}
		return finalDescr;
	}
	
	public NPC getNPC() {
		return npc;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public Item giveItem(String name) {
		for (Item i: items) {
			if (i.getName().equalsIgnoreCase(name)) {
				Item temp = i;
				items.remove(i);
				return temp;
			}
		}
		return null;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
}
