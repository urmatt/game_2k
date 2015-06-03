package zuul.welt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Enums.ZuulEnums.Directions;

public class Room {
	String name;

	HashMap<Directions, Room> exits = new HashMap<>();
	List<Thing> things = new ArrayList<Thing>();

	public Room(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addExit(Directions direction, Room room) {
		exits.put(direction, room);
	}

	public List<Directions> getExitsList() {
		List<Directions> exitsList = new ArrayList();
		exitsList.addAll(exits.keySet());
		return exitsList;
	}

	public boolean isOpen(Directions dir) {
		if (exits.get(dir) != null)
			return true;
		else
			return false;
	}

	public Room getExitingRoom(Directions dir) {
		return exits.get(dir);
	}

	public List<Thing> getThingsList() {
		return things;
	}

	public void addThing(Thing ding) {
		things.add(ding);
	}

	public String[] getThingNamesArray() {
		String[] list = new String[things.size()];
		for (int i = 0; i < list.length; i++) {
			list[i] = things.get(i).getName();
		}
		return list;
	}
}
