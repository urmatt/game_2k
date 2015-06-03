package zuul.welt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Enums.PanelNavigation.Directions;

public class Room {
	String name;
	
	HashMap<Directions, Room> exits = new HashMap<>();	
	
	public Room(String name) {
		this.name = name;
	}
	
	public void addExit(Directions direction, Room room){
		exits.put(direction, room);
	}
	
	public List<Directions> getExitsList(){
		List <Directions> exitsList = new ArrayList();
		exitsList.addAll(exits.keySet());
		return exitsList;
	}
	
	private boolean isOpen(Directions dir) {
		if(exits.get(dir) != null) return true;
		else return false;
	}
}
