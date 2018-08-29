package com.jfeather.Main;

import com.jfeather.Player.Character;
import com.jfeather.Player.Item;

public class NPC {

	/*
	 * This should be a rather simple class, and will mainly consist of pre-made NPC's
	 * The only things an NPC can do is either talk to the player or give them an item either right away, or after they do something
	 * TODO make a system so that the npc can be "helped" in some way
	 */
	
	public static final String NO_ITEM = "no_item";
	
	// The following are the times at which an NPC can give the player an item
	public static final String GIVE_ITEM_ON_MEETING = "on_meeting";
	public static final String GIVE_ITEM_ON_HELP = "on_help"; // This one can vary a bit, see the specific examples below for more info
	
	private String description, whenToGiveItem, firstInteractionText, subsequentInteractionText, finalInteractionText;
	private boolean hasMetPlayer, hasBeenHelped;
	private Item item;
	
	public NPC(String NPCDescription, String NPCfirstInteractionText, String NPCsubsequentInteractionText, String NPCfinalInteractionText, Item NPCitem, String NPCwhenToGiveItem) {
		item = NPCitem;
		whenToGiveItem = NPCwhenToGiveItem;
		firstInteractionText = NPCfirstInteractionText;
		subsequentInteractionText = NPCsubsequentInteractionText;
		finalInteractionText = NPCfinalInteractionText;
		hasMetPlayer = false;
		hasBeenHelped = false;
		description = NPCDescription;
	}
	
	public void interact(Character c) {
		if (!hasMetPlayer) {
			System.out.println(firstInteractionText);
			if (whenToGiveItem.equals(GIVE_ITEM_ON_MEETING)) {
				c.giveItem(item);
				item = null; // Make sure we don't give the player the item twice
			}
			hasMetPlayer = true;
		} else {
			if (!hasBeenHelped)
				// What will happen most of the time for the user (probably)
				System.out.println(subsequentInteractionText);
			else {
				System.out.println(finalInteractionText); // This will happen after the player has completed their "quest" and can't interact any further
				if (whenToGiveItem.equals(GIVE_ITEM_ON_HELP)) {
					c.giveItem(item);
					item = null; // Make sure we don't give the player the item twice
				}
			}
		}
		
	}
	
	public String getDescription() {
		return description;
	}
	
	public Item getItem() {
		return item;
	}
	
	public String getWhenToGiveItem() {
		return whenToGiveItem;
	}
	
	public String getFirstInteraction() {
		return firstInteractionText;
	}
	
	public String getSubsequentInteraction() {
		return subsequentInteractionText;
	}
	
	public String getFinalInteraction() {
		return finalInteractionText;
	}
}
