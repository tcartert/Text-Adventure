package me.todd.textadventure;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

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
	
	public JTextArea textArea;
	public JTextField textField;


	public static void main(String[] args) {
		instance = new Main();
		instance.createLocations();
		instance.createPlayer();
		instance.createGUI();
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
	
	private void createGUI() {
	    JFrame f = new JFrame("Text-Adventure");
	    f.setSize(700, 700);
	    f.setLocation(300,200);
	    textArea = new JTextArea(10, 40);
	    textArea.setEditable(false);
	    JScrollPane scroll = new JScrollPane(textArea);
	    f.getContentPane().add(BorderLayout.CENTER, scroll);
	    DefaultCaret caret = (DefaultCaret)textArea.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	    textField = new JTextField(30);
	    f.getContentPane().add(BorderLayout.SOUTH, textField);
	    textField.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	           Util.print(textField.getText());
	           parseCommand(textField.getText());
	           textField.setText("");
	        }
	    });

	    f.setVisible(true);
	}
	
	private void parseCommand(String cmd) {
		//DONE: 5
		String[] command = cmd.split(" ");
		if(command[0].equalsIgnoreCase("location")){
			Util.print(Player.getInstance().getCurrent().getName());
		}
		else if(command[0].equalsIgnoreCase("pickup")) {
			if(command.length > 1) {
			if(Player.getInstance().getCurrent().getItems().containsKey(command[1])) {
				Item item = Player.getInstance().getCurrent().getItems().get(command[1]);
				Player.getInstance().getInventory().addItem(item);
				Player.getInstance().getCurrent().removeItem(command[1]);
				Util.print("you have picked up " + item.getName());
			}
			}
		}
		
		else if(command[0].equalsIgnoreCase("items")) {
			StringBuilder sb = new StringBuilder();
			for(String item : Player.getInstance().getCurrent().getItems().keySet()) {
				sb.append(item + ", ");
			}
			Util.print("Items: " + sb.toString() + "\n");
		}
		
		//DONE: Task 6
		
		else if(command[0].equalsIgnoreCase("inventory")) {
			StringBuilder sb = new StringBuilder();
			for(Item item : Player.getInstance().getInventory().getItems()) {
				sb.append(item.getName() + ", ");
			}
			Util.print("Inventory: " + sb.toString());
		}
		
		//DONE: Task 8
		
		else if(command[0].equalsIgnoreCase("map")) {
			StringBuilder sb = new StringBuilder();
			Util.createMap(sb, Player.getInstance().getCurrent(),"", "", new ArrayList<String>());
			Util.print(sb.toString());
		}
		else {
		try {
			String l = Player.getInstance().getCurrent().getLinkedLocations().get(Direction.valueOf(command[0].toUpperCase()));
			Location loc = locations.get(l);
			if(Player.getInstance().getCurrent().getLinkedLocations().containsValue(l)) {
				Player.getInstance().setCurrent(loc);
				loc.setVisited();
			}
			
		}catch (IllegalArgumentException e) {
			Util.print("You cannot go that way.");
		}
		}
		startGame();
	}
	
	private void startGame() {
			
			//DONE: Task 2,4,7
		
			Player.getInstance().getCurrent().Display();
					
	}
}