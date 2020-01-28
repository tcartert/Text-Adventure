package me.todd.textadventure;

import java.util.HashMap;

public class Location {

	private String name;
	private String description;
	private HashMap<Direction,String> linkedLocations;
	
	public Location(String n, String desc) {
		this.name = n;
		this.description = desc;
		this.linkedLocations = new HashMap<>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Location addLink(Direction direction, String destination) {
		this.linkedLocations.put(direction, destination);
		return this;
	}
	
	public HashMap<Direction, String> getLinkedLocations(){
		return this.linkedLocations;
	}
	
	public Location build() {
		return this;
	}
	
	public void Display() {
		for(String loc: this.linkedLocations.values()) {
			System.out.print(Main.getInstance().getLocations().get(loc).getDescription());
		}
	}
	
}
