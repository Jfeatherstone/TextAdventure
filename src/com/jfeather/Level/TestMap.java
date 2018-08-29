package com.jfeather.Level;

import java.util.ArrayList;
import java.util.Arrays;

import com.jfeather.Main.GameArea;
import com.jfeather.Main.NPC;
import com.jfeather.Player.Item;

public class TestMap extends GameMap {

	// 	public GameArea(String levelDescription, NPC levelNPC, ArrayList<Item> levelItems)

	/*
	 * Different maps and what not can be created as their own class, and the user can just use the addArea method to customize it to their liking
	 * Above is the constructor for GameArea, for reference when you are creating your areas
	 * For how the program uses the key to assign locations to areas, see the GameMap class
	 */
	
	
	public TestMap() {
		addArea("0 0 0", new GameArea("Level description goes here for the first level", null, null));
		ArrayList<Item> list = new ArrayList<>();
		list.add(new Item("Pendant", "description goes here"));
		//list.add(new Item("Sword", "Used to hit things"));
		addArea("0 1 0", new GameArea("Level 2 goes here", null, list));
		addArea("0 0 1", new GameArea("Level 3 goes here", null, null));
	}
}
