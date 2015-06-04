package zuul.welt;

import Enums.ZuulEnums.Directions;

import java.util.ArrayList;

public class Player extends Actor{

	ArrayList<Thing> things = new ArrayList<Thing>();
	
	
	public Player(String name, Room room) {
		super(name, room);
	}
	
	public boolean thakeThing(int index){
        Thing th;
        if(actualRoom.isContains(index)){
            th = actualRoom.getMeThis(index);
            addThing(th);
            return true;
        }
            return false;
	}
	
	public void addThing(Thing th){
		things.add(th);
	}

    public String useThing(int index, Actor actor){
        String res = "";
        if(actor.isALive) {
            if (things.size() < index) {
                Thing th = things.get(index);
                res = th.useThingFor(actor);
            } else if (things.size() == 0) {
                res = name + " hat keine Dinge";
            } else if (index < 0) {
                res = "Whaele zu erst Etwas";
            }
        }

        return res;
    }

    public Thing getThing(int index){
        return things.get(index);
    }

	public void removeThing(Thing th){
		this.removeThing(th);
	}
	
	public void useThing(Thing th){
		th.makeUsed();
	}


    public String[] getThingsNamesArray(){
        String list [] = new String[things.size()];
        for(int i = 0; i < things.size(); i++){
            list[i] = things.get(i).getName();
        }
        return list;
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
