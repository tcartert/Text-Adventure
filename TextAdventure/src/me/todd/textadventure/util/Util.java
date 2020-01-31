package me.todd.textadventure.util;

import java.util.ArrayList;
import java.util.Iterator;

import me.todd.textadventure.Main;
import me.todd.textadventure.location.Location;

public class Util {

	public static void print(String msg) {
		Main.getInstance().textArea.append(msg + "\n");
	}
	
	//DONE: Task 8
	
	public static void createMap(StringBuilder buffer, Location loc, String prefix, String childrenPrefix, ArrayList<String> temp) {
		buffer.append(prefix);
		if(loc.getVisited()) {
			buffer.append(loc.getName());
		}else {
			buffer.append("Unknown");
		}
		buffer.append("\n");
		temp.add(loc.getName());
		ArrayList<Location> locs = new ArrayList<>();
		for(String s : loc.getLinkedLocations().values()){
			if(!temp.contains(s)) {
				locs.add(Main.getInstance().getLocations().get(s));
				temp.add(s);
			}
		}
		for(Iterator<Location> it  = locs.iterator(); it.hasNext(); ) {
			Location next = it.next();
			if(it.hasNext()) {
				createMap(buffer, next, childrenPrefix +"\u251C\u2500\u2500", childrenPrefix + "|   ", temp);
			}else {
				createMap(buffer, next, childrenPrefix + "\u2514\u2500\u2500 ", childrenPrefix + "|   ", temp);
			}
		}
	}

}
