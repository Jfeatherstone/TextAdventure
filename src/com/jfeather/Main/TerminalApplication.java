package com.jfeather.Main;

import java.util.Scanner;

import com.jfeather.Player.Character;

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
	 */
	
	public static final String TITLE = "Title goes here";
	public static final String RESOURCES_PATH = "Resources/";
	public static final double VERSION = 1.0;
	
	private static Thread gameLoop;
	
	public static void main(String[] args) {
		launch();
	}
	
	public static void init() {
		// Since the title just ran, we now want to get user input on which character they are going to play
		Scanner sc = new Scanner(System.in);
		// Just prompt the user for their name
		System.out.println("What is your name?");
		String name = sc.next();
		// We don't bother trying to loop until they get a valid name, if they don't get it the first try, we'll just list the possible saves
		
		// Now, we read in the save files and see if their name matched any of them
		Character[] possibleChars = Character.parseCharacters();
		boolean validChar = false;
		for (Character c: possibleChars)
			if (c.getName().equalsIgnoreCase(name))
				validChar = true;
		
		if (validChar) {
			// Assuming we have the character that we are playing, we can begin the game
			// TODO: that
		} else {
			// Since we didn't find the character they are looking for, we list the possible characters, or allow them to make a new save
			if (possibleChars.length > 0) {
				// TODO: Maybe make this more theme appropriate depending on what we decide for the theme and setting of the game
				System.out.println("Save not found!");
				System.out.println("Choose from any of the following saves of type \"new\" to create a new file!");
				for (Character c: possibleChars)
					System.out.println(c.getName());
				
				// Get user input
				// TODO:
				
			} else {
				// If there are no saves at all
				System.out.println("No saves were found!");
				System.out.println("Type \"new\" to create a new file!");
				
				// Get user input
				// TODO:
			}
		}
		
		sc.close();
		
	}
	
	public static void launch() {
		runTitle();
		init();
		startGameLoop();
	}
	
	public static void startGameLoop() {
		gameLoop = new Thread(() -> {
			while (true) {
				// There should be two things that happen here:
				// TODO: The program should take user input
				// TODO: The program should respond to the user input
				
				/*
				 * There should be more or less 3 categories of reactions to the user input:
				 * 1. Movement: this will change the player's location and could possible begin combat
				 * 2. Management: this includes checking inventory, saving, exiting, etc.
				 * 3. Combat: if the user is in combat, this will continue it, and if they are outside of it, it can initiate, if an enemy is nearby
				 */
			}
		});
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
