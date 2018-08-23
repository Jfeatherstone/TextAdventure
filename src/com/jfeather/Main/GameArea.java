package com.jfeather.Main;

import java.util.ArrayList;

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
	private ArrayList<String> items;
	private String description;
	
	// If any of these values are set to null, the program will assume that they don't exist in this level
	public GameArea(String levelDescription, NPC levelNPC, ArrayList<String> levelItems) {
		npc = levelNPC;
		items = levelItems;
		description = levelDescription;
	}
	
	public String getDescription() {
		return description;
	}
	
	public NPC getNPC() {
		return npc;
	}
	
	public ArrayList<String> getItems() {
		return items;
	}
}
