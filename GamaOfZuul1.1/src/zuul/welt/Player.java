package zuul.welt;

import java.util.ArrayList;

import Enums.ZuulEnums.Directions;
import Enums.ZuulEnums.ThingType;

public class Player {
	String name;
	
	Room actualRoom;
	
	ArrayList<Thing> things = new ArrayList<Thing>();
	
	
	public Player(String name, Room room) {
		this.name = name;
		actualRoom = room;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Room getActualRoom() {
		return actualRoom;
	}


	public void setActualRoom(Room actualRoom) {
		this.actualRoom = actualRoom;
	}
	
	public void thakeThing(Thing th){
		///////////////////
	}
	
	public void addThing(Thing th){
		things.add(th);
	}
	
	public void removeThing(Thing th){
		this.removeThing(th);
	}
	
	public void useThing(Thing th){
		th.makeUsed();
	}
	
	public String moveTo(Directions direction){
		String response = "Keine Ausgang in diesem Richtung";
		if(getActualRoom().isOpen(direction)){
			actualRoom = actualRoom.getExitingRoom(direction);
			response = "Ich habe in die Raum "+actualRoom.getName() + " bevegen";
			return response;
		}
		return response;
	}
}
