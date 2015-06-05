package zuul.welt;

import Enums.ZuulEnums;
import Enums.ZuulEnums.Directions;

import java.util.ArrayList;

public class Player extends Actor{

	ArrayList<Thing> things = new ArrayList<Thing>();

    public Player(String name){
        super(name, null);
    }
	
	private Player(String name, Room room) {
		super(name, room);
	}

    /**
     * Взять вешь под индексом :
     * @param index
     * @return Если удалось взять то true
     */

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

    /**
     * использовать вешь
     * @param thingToUse
     * @return результативный текст после использования вещи
     */
    public String useThing(Thing thingToUse){
        String res = "";
        if(actualRoom.containsMonster()) {
            if(thingToUse.type == ZuulEnums.ThingType.MEDICATION && actualRoom.getFirstActor() instanceof Monster)
            res = "Ich kann nicht Medikament fuer Monster benutzed";
            else {
                res = thingToUse.useThingFor(actualRoom.getFirstActor());
                things.remove(thingToUse);
            }
        }else res = thingToUse.getName()+" war zu Niemand benuzt";
        return res;
    }

    public Thing getThing(int index){
        return things.get(index);
    }

	public void removeThing(Thing th){
		this.removeThing(th);
	}

    public String[] getThingsNamesArray(){
        String list [] = new String[things.size()];
        for(int i = 0; i < things.size(); i++){
            list[i] = things.get(i).getName();
        }
        return list;
    }

    /**
     * можно скачать команда иди на лево(право, вверх, вниз)
     * @param direction на лево, право, вверх, вниз
     * @return Результативный текст после попытки передвижения
     */
	public String moveTo(Directions direction){
		String response = "Keine Ausgang in diesem Richtung";
		if(getActualRoom().isOpen(direction)){
			actualRoom = actualRoom.getExitingRoom(direction);
			response = "Ich habe in die Raum "+actualRoom.getName() + " bevegen " +
                    "\nIn Raum " + actualRoom.getMonstersList().size() + " Monster";
			return response;
		}
		return response;
	}
}
