package me.todd.textadventure;

import java.util.HashMap;
import java.util.Scanner;

import me.todd.textadventure.inventory.Item;

public class Main {

	private static Main instance;

	public static Main getInstance() {
		return instance;
	}

	private String[] directions = { "north", "south", "east", "west" };

	public String[] getDirections() {
		return directions;
	}

	private HashMap<String, Location> locations = new HashMap<>();

	public HashMap<String, Location> getLocations() {
		return locations;
	}

	public String[] inventory = {};

	private static Player player;

	public static void main(String[] args) {
		instance = new Main();
		instance.createLocations();
		instance.createPlayer();
		instance.startGame();

	}

	private void createPlayer() {
		player = new Player("test");
		player.setCurrent(locations.get("Woods"));
		//player.getCurrent().setVisited();

	}
	
	private void print(String msg) {
		System.out.print(msg + "\n");
	}

	private void createLocations() {
		locations.put("Woods",new Location("Woods", "You are in the woods. There are lots of trees.").addLink(Direction.NORTH, "Lake").addLink(Direction.EAST, "City").addLink(Direction.WEST, "Hills").build());
		locations.put("Lake", new Location("Lake", "You are by the lake. It is bery watery.").addLink(Direction.SOUTH, "Woods").addLink(Direction.EAST, "Swamp").addItem("sword-of-justice", new Item("Sword of justice", "Can be used to kill people")).build());
		locations.put("Swamp", new Location("Swamp", "*Squelch Squelch* What are you doing in ma swamp?").addLink(Direction.WEST, "Lake").build());
		locations.put("City", new Location("City", "In the City").addLink(Direction.WEST, "Woods").addLink(Direction.SOUTH, "Hills").build());
		locations.put("Hills", new Location("Hills", "You are in the hills. ").addLink(Direction.NORTH, "City").addLink(Direction.EAST, "Woods").build());
	}

	private void startGame() {
		while(true) {
			for(Direction dir : player.getCurrent().getLinkedLocations().keySet()){
				Location loc = locations.get(player.getCurrent().getLinkedLocations().get(dir));
				print(dir.toString() + ": (" + loc.hasVisited() + ") " + loc.getName());
			}
			player.getCurrent().Display();
			print("");
			for(Direction dir : player.getCurrent().getLinkedLocations().keySet()){
				print(dir.toString() + ": " + player.getCurrent().getLinkedLocations().get(dir));
			}
			Scanner scanner = new Scanner(System.in);
			String[] command = scanner.nextLine().split(" ");
			if(command[0].equalsIgnoreCase("location")){
				print(player.getCurrent().getName());
				continue;
			}
			if(command[0].equalsIgnoreCase("pickup")) {
				if(command.length > 1) {
				if(player.getCurrent().getItems().containsKey(command[1])) {
					Item item = player.getCurrent().getItems().get(command[1]);
					player.getInventory().addItem(item);
					player.getCurrent().removeItem(command[1]);
					print("you have picked up " + item.getName());
					continue;
				}
				}else {
					StringBuilder sb = new StringBuilder();
					for(String item : player.getCurrent().getItems().keySet()) {
						sb.append(item + ", ");
					}
					print("Items: " + sb.toString());
					continue;
				}
				continue;
			}
			
			if(command[0].equalsIgnoreCase("inventory")) {
				StringBuilder sb = new StringBuilder();
				for(Item item : player.getInventory().getItems()) {
					sb.append(item.getName() + ", ");
				}
				print("Inventory: " + sb.toString());
			}
			try {
				String l = player.getCurrent().getLinkedLocations().get(Direction.valueOf(command[0].toUpperCase()));
				Location loc = locations.get(l);
				if( player.getCurrent().getLinkedLocations().containsValue(l)) {
					player.setCurrent(loc);
					loc.setVisited();
				}
				continue;
				
			}catch (IllegalArgumentException e) {
				print("You cannot go that way.");
			}
					
		}
	}
}
