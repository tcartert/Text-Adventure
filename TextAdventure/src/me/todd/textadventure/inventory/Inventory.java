package me.todd.textadventure.inventory;

import java.util.ArrayList;

public class Inventory {

	private ArrayList<Item> items;
	
	public Inventory() {
		items = new ArrayList<>();
	}
	
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}
	
	public void removeItem(Item item) {
		if(this.items.contains(item)) {
			this.items.remove(item);
		}
	}
}
