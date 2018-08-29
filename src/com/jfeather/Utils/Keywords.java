package com.jfeather.Utils;

public final class Keywords {

	/*
	 * This class contains numerous arrays that help identify what the user would like to do in the game
	 * Every type of action, such as movement or combat, has two arrays:
	 * The first, is an array that must have at least one word in common with the input
	 * The second is an array that cannot appear at all in the phrase, otherwise it is disqualified as that type of action
	 * Many of the NOT_[Whatever] arrays are quite similar, because they contain the other categories' words
	 */
	
	// The 3 over-arching categories of actions
	public static final String MOVEMENT = "movement";
	public static final String[] MOVEMENT_KEYWORDS = {"move", "walk", "east", "west", "north", "south", "climb", "up", "down", "go"};
	public static final String[] NOT_MOVEMENT_KEYWORDS = {"attack", "pick", "take", "block", "stop", "inventory", "wait", "look", "hide",
			"dodge", "exit", "save", "quit", "help", "look", "see", "inspect"};

	public static final String MANAGEMENT = "management";
	public static final String[] MANAGEMENT_KEYWORDS = {"save", "quit", "exit", "take", "pick", "grab", "drop", "inventory", "item", "items", "wait",
			"help", "manual", "guide", "look", "talk", "ask", "say", "view", "see", "inspect"};
	public static final String[] NOT_MANAGEMENT_KEYWORDS = {"move", "walk", "east", "west", "north", "south", "climb", "up", "down",
			"go", "dodge", "attack", "block"};
	
	public static final String COMBAT = "combat";
	public static final String[] COMBAT_KEYWORDS = {"attack", "block", "dodge"};
	public static final String[] NOT_COMBAT_KEYWORDS = {"move", "walk", "east", "west", "north", "south", "climb", "up", "down", "go", "save", "quit",
			"exit", "take", "pick", "grab", "inventory", "item", "items", "wait","help", "manual", "guide", "look", "talk", "ask", "say", "view", "see", "inspect"};
	
	public static final String OTHER = "other";
	public static final String[] OTHER_PHRASES = {"I don't quite understand that!", "An interesting idea, but not quite practical.",
			"I'm not sure about that one!", "Let's try something else!", "Do you hear yourself?", "I'm sorry?"};
	
	public static final String[] NO_NPC_PHRASES = {"No one responds!", "You seem to be all alone!", "Talking to yourself won't help!", "Even your imaginary friends won't talk to you!",
			"Maybe if you yell louder, someone will care!"};
	public static final String[] NO_ITEM_PHRASES = {"You can't seem to find that item!", "What item?"};
	// More specific keyword associations
	
	public static String determineCategory(String input) {
		if (Contains.arrElementsInString(MOVEMENT_KEYWORDS, input) != -1 && Contains.arrElementsInString(NOT_MOVEMENT_KEYWORDS, input) == -1)
			return MOVEMENT;
		if (Contains.arrElementsInString(MANAGEMENT_KEYWORDS, input) != -1 && Contains.arrElementsInString(NOT_MANAGEMENT_KEYWORDS, input) == -1)
			return MANAGEMENT;
		if (Contains.arrElementsInString(COMBAT_KEYWORDS, input) != -1 && Contains.arrElementsInString(NOT_COMBAT_KEYWORDS, input) == -1)
			return COMBAT;
		return OTHER;
	}
}
