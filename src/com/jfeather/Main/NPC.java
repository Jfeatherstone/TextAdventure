package com.jfeather.Main;

import com.jfeather.Player.Character;

public class NPC {

	/*
	 * This should be a rather simple class, and will mainly consist of pre-made NPC's
	 * The only things an NPC can do is either talk to the player or give them an item either right away, or after they do something
	 */
	
	public static final String NO_ITEM = "no_item";
	
	// The following are the times at which an NPC can give the player an item
	public static final String GIVE_ITEM_ON_MEETING = "on_meeting";
	public static final String GIVE_ITEM_ON_HELP = "on_help"; // This one can vary a bit, see the specific examples below for more info
	
	private String item, whenToGiveItem, firstInteractionText, subsequentInteractionText, finalInteractionText;
	private boolean hasMetPlayer, hasBeenHelped;
	
	public NPC(String NPCfirstInteractionText, String NPCsubsequentInteractionText, String NPCfinalInteractionText, String NPCitem, String NPCwhenToGiveItem) {
		item = NPCitem;
		whenToGiveItem = NPCwhenToGiveItem;
		firstInteractionText = NPCfirstInteractionText;
		subsequentInteractionText = NPCsubsequentInteractionText;
		finalInteractionText = NPCfinalInteractionText;
		hasMetPlayer = false;
		hasBeenHelped = false;
	}
	
	public void interact(Character c) {
		if (!hasMetPlayer) {
			System.out.println(firstInteractionText);
			if (whenToGiveItem.equals(GIVE_ITEM_ON_MEETING))
				c.giveItem(item);
			hasMetPlayer = true;
		} else {
			
		}
		
	}
	
	public String getItem() {
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
