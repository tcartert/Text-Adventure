package me.todd.textadventure;

import me.todd.textadventure.inventory.Inventory;

public class Player {

	private String name;
	private Location Current;
	private Inventory inventory;
	
	public Player(String n) {
		this.name = n;
		this.inventory = new Inventory();
	}
	
	public String getName() {
		return this.name;
	}
	
	public Location getCurrent() {
		return this.Current;
	}
	
	public void setCurrent(Location loc) {
		this.Current = loc;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
}
