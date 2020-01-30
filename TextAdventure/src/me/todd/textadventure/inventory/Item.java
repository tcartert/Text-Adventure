package me.todd.textadventure.inventory;

public class Item {

	//Done: Task 3
	
	private String name;
	private String description;
	
	public Item(String n, String desc) {
		this.name = n;
		this.description = desc;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
}
