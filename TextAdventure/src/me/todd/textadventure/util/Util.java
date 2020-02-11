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
	
	public static void createMap(StringBuilder sb, Location loc, String prefix, String childrenPrefix, ArrayList<String> temp) {
		sb.append(prefix);
		if(loc.getVisited()) {
			sb.append(loc.getName());
		}else {
			sb.append("Unknown");
		}
		sb.append("\n");
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
				createMap(sb, next, childrenPrefix +"\u251C\u2500\u2500 ", childrenPrefix + "\uFF5C   ", temp);
			}else {
				createMap(sb, next, childrenPrefix + "\u2514\u2500\u2500 ", childrenPrefix + "\uFF5C   ", temp);
			}
		}
	}

}
