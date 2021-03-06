package com.jfeather.Main;

import java.util.Random;
import java.util.Scanner;

import com.jfeather.Level.GameMap;
import com.jfeather.Level.TestMap;
import com.jfeather.Player.Character;
import com.jfeather.Player.Item;
import com.jfeather.Utils.Contains;
import com.jfeather.Utils.Keywords;

public class TerminalApplication {
	
	/*
	 * For the boot up screen:
	 * Should run the title and say a bit about the game or something
	 * Then it will prompt the user, what is your name?
	 * It will then read in the save files and see if that name matches any
	 * If it does, the game will begin using that character, otherwise, it will list the available characters
	 * or allow the user to type "new" to create a new character
	 * 
	 * For saving:
	 * type "save" in the terminal, and it will automatically save the character to the file
	 * Maybe use some unique key for each creation, so you can tell if the game is overwriting an old character with the same name
	 * And ask the user whether they really want to delete the old data
	 * 
	 * Level Design:
	 * The program will be able to to read in a txt file or something that has all of the defined variables in it.
	 * It should save a fresh copy of the level in case you want to restart or something maybe in an archive folder
	 * All weapons and NPC's should be defined in this file as well, because they will be read in at the same time
	 * Whenever the player saves, they should change the level file to match what is currently going on in the game
	 * This means any items the player may have picked up, their progression with NPC's, monsters killed, etc.
	 * 
	 */
	
	public static final String TITLE = "Title goes here";
	public static final String RESOURCES_PATH = "Resources/";
	public static final double VERSION = 1.0;
	
	private static Thread gameLoop;
	private static Scanner sc;
	private static Random rng;
	private static Character character; // This is where the read in or created character will be assigned
	private static GameArea currentArea;
	public static void main(String[] args) {
		launch();
	}
	
	public static void init() {
		// Since the title just ran, we now want to get user input on which character they are going to play
		sc = new Scanner(System.in);
		rng = new Random();
		
		// Just prompt the user for their name
		System.out.println("What is your name?");
		System.out.print(">>> ");
		String name = sc.nextLine();
		// We don't bother trying to loop until they get a valid name, if they don't get it the first try, we'll just list the possible saves
		
		// Now, we read in the save files and see if their name matched any of them
		Character[] possibleChars = Character.parseCharacters();
		boolean validChar = false;
		for (Character c: possibleChars) {
			if (c.getName().equalsIgnoreCase(name)) {
				validChar = true;
				character = c; // Save the character to be used during gameplay and pretty much everything else
			}
		}
		if (validChar) {
			// Assuming we have the character that we are playing, we can begin the game
			// We have already assigned the variable 'character' so there really isn't much else to do here functionally
			// I may want to put something here about either lore or an overview of the character's stats, but that may happen
			// in the gameloop, because it should happen no matter what
		} else {
			// Since we didn't find the character they are looking for, we list the possible characters, or allow them to make a new save
			if (possibleChars.length > 0) {
				// TODO: Maybe make this more theme appropriate depending on what we decide for the theme and setting of the game
				System.out.println("Save not found!");
				// This while loop is so that if the user is a monkey and can't read directions, they have multiple chances to enter a name
				while (true) {
					System.out.println("Choose from any of the following saves or type \"new\" to create a new file!");
					// Print out the loaded characters
					for (Character c: possibleChars)
						System.out.println(c.getName());
					
					// Get user input
					System.out.print(">>> ");
					name = sc.nextLine();
	
					// See if we are creating a new character
					if (name.equalsIgnoreCase("new")) {
						System.out.println("Creating a new character...");
						System.out.println("What is your name?");
						System.out.print(">>> ");
						name = sc.nextLine();
					}
					
					// Otherwise, we check to see if the user selected a correct profile
					for (Character c: possibleChars) {
						if (c.getName().equalsIgnoreCase(name)) {
							character = c; // Save the character to be used during gameplay and pretty much everything else
							break;
						}
					}
				}
			} else {
				// If there are no saves at all
				System.out.println("No saves were found!");
				while (true) {
					System.out.println("Type \"new\" to create a new file!");
					
					// Get user input
					System.out.print(">>> ");
					name = sc.nextLine();
					
					if (name.equalsIgnoreCase("new")) {
						System.out.println("Creating a new character...");
						System.out.println("What is your name?");
						System.out.print(">>> ");
						name = sc.nextLine();
						
						// Create our new save file and then we can move on to the actual game
						character = new Character(name);
						character.saveCharacter();
						
						// Exit the loop because we have what we need
						break;
					}
				}
				
			}
		}
		
		//sc.close();
		// We can't close this here because we use the same scanner later
		
	}
	
	public static void launch() {
		runTitle();
		init();
		startGameLoop();
	}
	
	public static void startGameLoop() {
		// First, we grab the game map
		GameMap map = new TestMap();
		currentArea = map.begin();
		System.out.println();
		System.out.println(currentArea.getDescription());
		
		character.giveItem(new Item("Potion", "This is a magical potion!"));
		
		// When the player starts the game, we want to print out their stats (health, inventory, etc.)
		// As well as the description of their current location where they left off
		// This can be in this method as opposed to init() because it should happen for both new characters and loaded ones
		// TODO
		// Turns out we don't actually need a new thread here, so this next line is just sorta legacy 
		//gameLoop = new Thread(() -> {
		while (true) {
			// Take user input
			System.out.print(">>> ");
			String input = sc.nextLine();
			//System.out.println(input);
			
			// Just to separate the text so it's easier to read
			System.out.println();
			
			// Next, we have to sort the user input such that we know what type of action the player wants to take (see below)
			// Maybe run the user input through a method normalizes it into a few categories (movement, management, combat)
			// and then just check every single keyword in that category to see what the game should respond with
			/*
			 * There should be more or less 3 categories of user input:
			 * 1. Movement: this will change the player's location and could possibly begin combat
			 * 		- go east/west/north/south
			 * 		- climb up/down
			 * 2. Management: this includes checking inventory, saving, exiting, etc.
			 * 		- save
			 * 		- quit
			 * 		- inventory
			 * 		- take [item]
			 * 		- drop [item]
			 * 		- items
			 * 		- wait
			 * 		- look
			 * 3. Combat: if the user is in combat, this will continue it, and if they are outside of it, it can initiate it, if an enemy is nearby
			 * 		- attack
			 * 		- attack with [weapon]
			 * 		- defend
			 * 		- defend with [weapon]
			 * 		- block
			 * 		- block with [weapon]
			 * 		- run (?)
			 * 		- hide (?)
			 * 		- dodge
			 */
			
			String category = Keywords.determineCategory(input);
			
			switch (category) {
			case Keywords.OTHER:
				// Just randomly respond with some message about not understanding the input
				System.out.println(Keywords.OTHER_PHRASES[rng.nextInt(Keywords.OTHER_PHRASES.length)]);
				break;
			case Keywords.MOVEMENT:
				/*
				 * There are several checks for valid directions here, so I'll do my best to explain them
				 * The program first makes sure that the user has not only used one of the keywords from the Keywords class, but also has a valid direction (east, west, etc.)
				 * The program will then check which direction it was set to go, and see if there is an area that can be accessed there
				 * If there is, the program will get that level, which then changes the game map's variables and prints the area description
				 */
				if (Contains.arrElementsAsWordsInString(new String[] {"east", "west", "north", "south", "up", "down"}, input) != -1) {
					if (Contains.wordInString("east", input)) {
						if (map.isThereARoomToThe(GameMap.EAST)) {
							currentArea = map.getRoomToThe(GameMap.EAST);
							System.out.println(currentArea.getDescription());
						} else
							System.out.println("You can't go that way!");
					}
					else if (Contains.wordInString("west", input)) {
						if (map.isThereARoomToThe(GameMap.WEST)) {
							currentArea = map.getRoomToThe(GameMap.WEST);
							System.out.println(currentArea.getDescription());
						} else
							System.out.println("You can't go that way!");
					}
					else if (Contains.wordInString("north", input)) {
						if (map.isThereARoomToThe(GameMap.NORTH)) {
							currentArea = map.getRoomToThe(GameMap.NORTH);
							System.out.println(currentArea.getDescription());
						} else
							System.out.println("You can't go that way!");
					}
					else if (Contains.wordInString("south", input)) {
						if (map.isThereARoomToThe(GameMap.SOUTH)) {
							currentArea = map.getRoomToThe(GameMap.SOUTH);
							System.out.println(currentArea.getDescription());
						} else
							System.out.println("You can't go that way!");
					}
					else if (Contains.wordInString("up", input)) {
						if (map.isThereARoomToThe(GameMap.UP)) {
							currentArea = map.getRoomToThe(GameMap.UP);
							System.out.println(currentArea.getDescription());
						} else
							System.out.println("You can't go that way!");
					}
					else if (Contains.wordInString("down", input)) {
						if (map.isThereARoomToThe(GameMap.DOWN)) {
							currentArea = map.getRoomToThe(GameMap.DOWN);
							System.out.println(currentArea.getDescription());
						} else
							System.out.println("You can't go that way!");
					} else
						System.out.println("You can't go that way!");
				} else {
					System.out.println("I don't recognize that as a valid direction!");
				}
				break;
			case Keywords.MANAGEMENT:
				//System.out.println(category);
				// TODO
				// Check to see if they player wants to look around
				if (Contains.arrElementsAsWordsInString(new String[] {"look", "view", "see"}, input) != -1)
					System.out.println(currentArea.getDescription());
				
				// Inspect gets its own category because it has multiple uses
				// It can either look around, or look at an item
				if (Contains.wordInString("inspect", input)) {
					String[] words = Contains.separateBySpacesIntoArray(input);
					if (words.length < 2) {
						// Just look around the area
						System.out.println(currentArea.getDescription());
					} else {
						// We search through the player's items and the items in the level and look at them
						boolean done = false;
						for (int i = 0; i < character.getItems().size(); i++) {
							if (Contains.string2InString1(input, character.getItems().get(i).getName())) {
								System.out.println(character.getItems().get(i).getName() + ":");
								System.out.println(character.getItems().get(i).getDescription());
								done = true;
								break;
							}
						}
						// TODO look through the area for any items matching the name
						for (int i = 0; i < currentArea.getItems().size(); i++) {
							if (Contains.string2InString1(input, currentArea.getItems().get(i).getName())) {
								System.out.println(currentArea.getItems().get(i).getName() + ":");
								System.out.println(currentArea.getItems().get(i).getDescription());
								done = true;
								break;
							}
						}
						if (!done)
							System.out.println(Keywords.NO_ITEM_PHRASES[rng.nextInt(Keywords.NO_ITEM_PHRASES.length)]);
					}
				}

				
				// If the player wants to see their inventory
				if (Contains.arrElementsAsWordsInString(new String[] {"inventory", "items", "item"}, input) != -1)
					character.printItems();
				
				// If the player wants to exit the game
				if (Contains.arrElementsAsWordsInString(new String[] {"exit", "quit"}, input) != -1) {
					System.out.println("Would you like to save before exiting?");
					System.out.println("(Yes, No, Cancel)");
					String nextInput = sc.nextLine();
					if (nextInput.equalsIgnoreCase("yes")) {
						// TODO save and quit here
						character.saveCharacter();
						System.exit(0);
					} else if (nextInput.equalsIgnoreCase("no")) {
						System.exit(0);
					} else {
						System.out.println("Game will now resume!");
						// Do nothing, because we don't want to exit the program
					}
				}
				
				// If the player wants to take an item from a level
				if (Contains.arrElementsAsWordsInString(new String[] {"take", "pick", "grab"}, input) != -1) {
					String[] words = Contains.separateBySpacesIntoArray(input);
					outside:
					if (currentArea.getItems() != null) {
						for (int i = 0; i < currentArea.getItems().size(); i++) {
							for (int j = 0; j < words.length; j++) {
								if (currentArea.getItems().get(i).getName().equalsIgnoreCase(words[j])) {
									// Give the item to the player and remove it from the level
									// TODO this might not be done yet, not quite sure
									System.out.println("You got a " + currentArea.getItems().get(i).getName() + "!");
									character.giveItem(currentArea.giveItem(words[j]));
									//currentArea.getItems().remove(i);
									break outside;
								}
							}
						}
						System.out.println(Keywords.NO_ITEM_PHRASES[rng.nextInt(Keywords.NO_ITEM_PHRASES.length)]);
					}
				}
				
				// If the player wants to drop an item on in an area
				if (Contains.wordInString("drop", input)) {
					boolean done = false;
					for (int i = 0; i < character.getItems().size(); i++) {
						if (Contains.string2InString1(input, character.getItems().get(i).getName())) {
							System.out.println("Dropped " + character.getItems().get(i).getName() + "!");
							currentArea.getItems().add(character.getItems().get(i));
							character.getItems().remove(i);
							done = true;
							break;
						}
					}
					if (!done)
						System.out.println(Keywords.NO_ITEM_PHRASES[rng.nextInt(Keywords.NO_ITEM_PHRASES.length)]);

				}
				
				// If the player wants to see the help screen
				if (Contains.arrElementsAsWordsInString(new String[] {"help", "manual", "guide"}, input) != -1) {
					// Nothing too crazy, most of this is just for formatting, so this really shouldn't need to be changed at any point
					System.out.println("Any of the following keywords are valid:");
					System.out.println("Movement - ");
					for (String s: Keywords.MOVEMENT_KEYWORDS) {
						System.out.print(s + " ");
					}
					System.out.println("\nManagement - ");
					for (String s: Keywords.MANAGEMENT_KEYWORDS) {
						System.out.print(s + " ");
					}
					System.out.println("\nCombat - ");
					for (String s: Keywords.COMBAT_KEYWORDS) {
						System.out.print(s + " ");
					}
					System.out.println();

				}

				// If the player wants to interact with any NPCs in the area
				if (Contains.arrElementsAsWordsInString(new String[] {"talk", "ask", "say"}, input) != -1) {
					if (currentArea.getNPC() != null) {
						currentArea.getNPC().interact(character);
					} else {
						System.out.println(Keywords.NO_NPC_PHRASES[rng.nextInt(Keywords.NO_NPC_PHRASES.length)]);
					}
				}
				
				break;
			case Keywords.COMBAT:
				System.out.println(category);
				// TODO
				break;
			}
												
		}
		//});
		//gameLoop.start();
	}
	
	public static void runTitle() {
		// Just show the startup screen and some other info
		// This method does NOT fetch the user input and initialize the characters; for that, see init()
		
		// This seems complicated because it centers the title and version text relative to the dashed lines
		String dashes = "------------------------------------------------------------------"; // Just so we can get the length of this
		String spaces = "";
		for (int i = 0; i < (dashes.length() - TITLE.length() - 6) / 2; i++) { // The + 6 accounts for the dashes
			spaces += " ";
		}
		System.out.println(dashes);
		System.out.println(spaces + "---" + TITLE + "---");
		System.out.println(dashes);
		spaces = "";
		for (int i = 0; i < (dashes.length() - (VERSION +"").length() - 3) / 2; i++) { // The + 3 accounts for the dashes and the V
			spaces += " ";
		}
		System.out.println(spaces + "-V" + VERSION + "-");

		// TODO: Maybe some info about the story behind the game here, idk
		
		
	}
}
