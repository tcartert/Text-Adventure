package me.todd.textadventure;

import java.util.ArrayList;
import java.util.HashMap;

import me.todd.textadventure.inventory.Item;

public class Location {

	private String name;
	private String description;
	private HashMap<Direction,String> linkedLocations;
	private HashMap<String, Item> items;
	private Boolean visited;
	
	public Location(String n, String desc) {
		this.name = n;
		this.description = desc;
		this.linkedLocations = new HashMap<>();
		items = new HashMap<>();
		visited = false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setVisited() {
		this.visited = true;
	}
	
	public String hasVisited() {
		if(this.visited) return "VISITED";
		return "NEW";
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Location addLink(Direction direction, String destination) {
		this.linkedLocations.put(direction, destination);
		return this;
	}
	
	public Location addItem(String name, Item item) {
		this.items.put(name, item);
		return this;
	}
	
	public HashMap<String, Item> getItems(){
		return this.items;
	}
	
	public HashMap<Direction, String> getLinkedLocations(){
		return this.linkedLocations;
	}
	
	public void removeItem(String item) {
		this.items.remove(item);
	}
	
	public Location build() {
		return this;
	}
	
	public void Display() {
			System.out.print("(" + hasVisited() + ") " + this.getName() + " - " + this.getDescription() + " (" + this.items.size()  + " Items)\n");
	}
	
}
