package me.todd.textadventure;

import java.util.HashMap;
import java.util.Scanner;

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

	}
	
	private void print(String msg) {
		System.out.print(msg + "\n");
	}

	private void createLocations() {
		locations.put("Woods",new Location("Woods", "You are in the woods. There are lots of trees.").addLink(Direction.NORTH, "Lake").addLink(Direction.EAST, "City").addLink(Direction.WEST, "Hills").build());
		locations.put("Lake", new Location("Lake", "You are by the lake. It is bery watery.").addLink(Direction.SOUTH, "Woods").addLink(Direction.EAST, "Swamp").build());
		locations.put("Swamp", new Location("Swamp", "*Squelch Squelch* What are you doing in ma swamp?").addLink(Direction.WEST, "Lake").build());
		locations.put("City", new Location("City", "In the City").addLink(Direction.WEST, "Woods").addLink(Direction.SOUTH, "Hills").build());
		locations.put("Hills", new Location("Hills", "You are in the hills. ").addLink(Direction.NORTH, "City").addLink(Direction.EAST, "Woods").build());
	}

	private void startGame() {
		while(true) {
			print(player.getCurrent().getDescription());
			for(Direction dir : player.getCurrent().getLinkedLocations().keySet()){
				print(dir.toString() + ": " + player.getCurrent().getLinkedLocations().get(dir));
			}
			Scanner scanner = new Scanner(System.in);
			String command = scanner.nextLine();
			if(command.equalsIgnoreCase("location")){
				print(player.getCurrent().getName());
				continue;
			}
			try {
				String loc = player.getCurrent().getLinkedLocations().get(Direction.valueOf(command.toUpperCase()));
				player.setCurrent(locations.get(loc));
				continue;
				
			}catch (IllegalArgumentException e) {
				print("You cannot go that way.");
			}
					
		}
	}
}
