package com.jfeather.Level;

import java.util.HashMap;

import com.jfeather.Main.GameArea;

public class GameMap {

	public static final String EAST = "east";
	public static final String WEST = "west";
	public static final String NORTH = "north";
	public static final String SOUTH = "south";
	public static final String UP = "up";
	public static final String DOWN = "down";
	
	/*
	 * So this one is a bit of a stretch, but bear with me here, because it might be a bit complicated (and a lot badly explained)
	 * The problem is that we need a way to link all of the levels together in a linked-list style
	 * We need to be able to have a room, and be able to get all of the adjacent rooms only given that room and a direction to go
	 * My solution to this problem is to create a hashmap that is based off a key that changes based on the direction you go
	 * The player will always start at the location 0, 0, 0 and the following permutations will be associated with movement;
	 * 		East - this will add 1 to the first key
	 * 		West - this will subtract 1 from the first key
	 * 		North - this will add 1 to the second key
	 * 		South - this will subtract 1 from the second key
	 * 		Up - this will add 1 to the third key
	 * 		Down - this will subtract 1 from the third key
	 * 
	 * Essentially, this will play with the individual digits of the key, so for example:
	 * If the key is 150, the player has gone up 1 time and south 5 times
	 * The game areas will then be added according to this convention at a particular key in the hashmap
	 */
	
	private int eastWestKey, northSouthKey, upDownKey;
	private HashMap<String, GameArea> gameMap;
	private String key;
	
	public GameMap() {
		gameMap = new HashMap<>();
	}
	
	public GameArea begin() {
		eastWestKey = 0;
		northSouthKey = 0;
		upDownKey = 0;
		key = eastWestKey + " " + northSouthKey + " " + upDownKey;
		
		return gameMap.get(key);
	}
	
	public void addArea(String key, GameArea newArea) {
		gameMap.put(key, newArea);
	}
	/*
	 * This method was created because I was previously using just getRoomToThe() and seeing if it was null
	 * but this is a big no no, because it acts as though you are progressing in that direction twice,
	 * but at that point we have passed the null check, so we get an uncatchable null pointer
	 */
	public boolean isThereARoomToThe(String direction) {
		// If the room ends up being null, then we need to restore the old keys, because we didn't actually move anywhere
		int tempKey1 = eastWestKey;
		int tempKey2 = northSouthKey;
		int tempKey3 = upDownKey;
		
		switch (direction) {
			case EAST:
				tempKey1++;
				break;
			case WEST:
				tempKey1--;
				break;
			case NORTH:
				tempKey2++;
				break;
			case SOUTH:
				tempKey2--;
				break;
			case UP:
				tempKey3++;
				break;
			case DOWN:
				tempKey3--;
				break;
			default:
				// We don't want to return anything if the direction is somehow wrong, though it really shouldn't be able to be wrong
				return false;
		}
		
		String tempKey = tempKey1 + " " + tempKey2 + " " + tempKey3;
		//System.out.println(key);
				
		return gameMap.get(tempKey) != null;

	}
	
	public GameArea getRoomToThe(String direction) {
		// If the room ends up being null, then we need to restore the old keys, because we didn't actually move anywhere
		int oldKey1 = eastWestKey;
		int oldKey2 = northSouthKey;
		int oldKey3 = upDownKey;
		
		switch (direction) {
			case EAST:
				eastWestKey++;
				break;
			case WEST:
				eastWestKey--;
				break;
			case NORTH:
				northSouthKey++;
				break;
			case SOUTH:
				northSouthKey--;
				break;
			case UP:
				upDownKey++;
				break;
			case DOWN:
				upDownKey--;
				break;
			default:
				// We don't want to return anything if the direction is somehow wrong, though it really shouldn't be able to be wrong
				return null;
		}
		
		key = eastWestKey + " " + northSouthKey + " " + upDownKey;
		//System.out.println(key);

		if (gameMap.get(key) == null) {
			eastWestKey = oldKey1;
			northSouthKey = oldKey2;
			upDownKey = oldKey3;
			return null;
		}
		
		key = eastWestKey + " " + northSouthKey + " " + upDownKey;
		//System.out.println(key);
		//System.out.println(gameMap.get(key));
		
		return gameMap.get(key);
	}
	
}
