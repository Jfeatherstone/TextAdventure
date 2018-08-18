package com.jfeather.Items;

public class Weapon {

	/*
	 * Although this class is called weapon, it includes both offensive and defensive equipable items
	 * The game will not randomly generate weapons, but rather will have several pre-defined to make them more balanced
	 */
	
	private int damage, block;
	private String name, description;
	private double levelMod;
	
	private Weapon(String itemName, String itemDescr, int itemDamage, int itemBlock, double itemLevelMod) {
		damage = itemDamage;
		block = itemBlock;
		name = itemName;
		description = itemDescr;
		levelMod = itemLevelMod;
	}
	
	// We don't need any setters for the weapons, because they cannot be changed
	// As far as scaling with level, each weapon has a scale modifier that is applied during combat
	
	public int getDamage() {
		return damage;
	}
	
	public int getBlock() {
		return block;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getLevelModifier() {
		return levelMod;
	}
	
	// Here are the pre-defined weapons that will be available in the game
	// Their locations will more or less not be random, though some weapons that are a similar tier may have a pool of locations
	// TODO: make all of these items
	
}
