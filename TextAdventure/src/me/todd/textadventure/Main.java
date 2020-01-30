package me.todd.textadventure;

import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import me.todd.textadventure.inventory.Item;
import me.todd.textadventure.location.Direction;
import me.todd.textadventure.location.Location;
import me.todd.textadventure.player.Player;
import me.todd.textadventure.util.Util;

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


	public static void main(String[] args) {
		instance = new Main();
		instance.createLocations();
		instance.createPlayer();
		instance.startGame();

	}

	private void createPlayer() {
		Player.getInstance().setCurrent(locations.get("Woods"));
		Player.getInstance().getCurrent().setVisited();

	}
	
	private void createLocations() {
		locations.put("Woods",new Location("Woods", "You are in the woods. There are lots of trees.").addLink(Direction.NORTH, "Lake").addLink(Direction.EAST, "City").addLink(Direction.WEST, "Hills").build());
		locations.put("Lake", new Location("Lake", "You are by the lake. It is bery watery.").addLink(Direction.SOUTH, "Woods").addLink(Direction.EAST, "Swamp").addItem("sword-of-justice", new Item("Sword of justice", "Can be used to kill people")).build());
		
		// DONE: Task 1 
		
		locations.put("Swamp", new Location("Swamp", "*Squelch Squelch* What are you doing in ma swamp?").addLink(Direction.WEST, "Lake").addItem("ogre-killer", new Item("Ogre Killer", "Kill ogres")).build());
		locations.put("City", new Location("City", "In the City").addLink(Direction.WEST, "Woods").addLink(Direction.SOUTH, "Hills").build());
		locations.put("Hills", new Location("Hills", "You are in the hills. ").addLink(Direction.NORTH, "City").addLink(Direction.EAST, "Woods").build());
	}
	
	private void startGame() {
		while(true) {
			
			//DONE: Task 2,4,7
			
			Player.getInstance().getCurrent().Display();
			
			Scanner scanner = new Scanner(System.in);
			String[] command = scanner.nextLine().split(" ");
			if(command[0].equalsIgnoreCase("location")){
				Util.print(Player.getInstance().getCurrent().getName());
				continue;
			}
			
			//DONE: 5
			
			if(command[0].equalsIgnoreCase("pickup")) {
				if(command.length > 1) {
				if(Player.getInstance().getCurrent().getItems().containsKey(command[1])) {
					Item item = Player.getInstance().getCurrent().getItems().get(command[1]);
					Player.getInstance().getInventory().addItem(item);
					Player.getInstance().getCurrent().removeItem(command[1]);
					Util.print("you have picked up " + item.getName());
					continue;
				}
				}
				continue;
			}
			
			if(command[0].equalsIgnoreCase("items")) {
				StringBuilder sb = new StringBuilder();
				for(String item : Player.getInstance().getCurrent().getItems().keySet()) {
					sb.append(item + ", ");
				}
				Util.print("Items: " + sb.toString() + "\n");
				continue;
			}
			
			//DONE: Task 6
			
			if(command[0].equalsIgnoreCase("inventory")) {
				StringBuilder sb = new StringBuilder();
				for(Item item : Player.getInstance().getInventory().getItems()) {
					sb.append(item.getName() + ", ");
				}
				Util.print("Inventory: " + sb.toString());
			}
			try {
				String l = Player.getInstance().getCurrent().getLinkedLocations().get(Direction.valueOf(command[0].toUpperCase()));
				Location loc = locations.get(l);
				if(Player.getInstance().getCurrent().getLinkedLocations().containsValue(l)) {
					Player.getInstance().setCurrent(loc);
					loc.setVisited();
				}
				continue;
				
			}catch (IllegalArgumentException e) {
				Util.print("You cannot go that way.");
			}
					
		}
	}
}
