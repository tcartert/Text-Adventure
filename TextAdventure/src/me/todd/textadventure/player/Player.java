package me.todd.textadventure.player;

import me.todd.textadventure.inventory.Inventory;
import me.todd.textadventure.location.Location;

public class Player {

	private static Player player_instance = new Player();
	
	private Location Current;
	private Inventory inventory;
	
	public Player() {
		this.inventory = new Inventory();
	}
	
	public static Player getInstance() {
		return player_instance;
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
