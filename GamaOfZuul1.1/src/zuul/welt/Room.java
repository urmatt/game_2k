package zuul.welt;

import Enums.ZuulEnums.Directions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {
	String name;

    /**
     * Для выходов как ключь использован enum значения Смотреть класс ZuulEnums
     */

	HashMap<Directions, Room> exits = new HashMap<Directions, Room>();

    //Список вешей которые находятся в комнате
	List<Thing> things = new ArrayList<Thing>();

    //Список монстров которые находятся именно в этой комнате
    ArrayList<Actor> monsters = new ArrayList<Actor>();

	public Room(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    //Связывание комнат
	public void addExit(Directions direction, Room room) {
		exits.put(direction, room);
	}

    //Отвечает есть ли в этой комнате монстры
    public boolean containsMonster(){
        if(monsters.size()>0)return true;
        else return false;
    }

    //Возвращает список всех выходов из этой комнаты
	public List<Directions> getExitsList() {
		List<Directions> exitsList = new ArrayList();
		exitsList.addAll(exits.keySet());
		return exitsList;
	}

    //проверка есть ли выход в данном направлении
	public boolean isOpen(Directions dir) {
		if (exits.get(dir) != null)
			return true;
		else
			return false;
	}

    /**
     *
     * @param dir направление на выход
     * @return выход
     */
	public Room getExitingRoom(Directions dir) {
		return exits.get(dir);
	}

    /**
     * @return список всех вещей в этой комнате
     */
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

    public Thing getMeThis(int index){
        Thing thing = things.get(index);
        things.remove(thing);
        return thing;
    }

    public boolean isContains(int index){//0
        if(things.size() >= 0 && index < things.size() && index >= 0)
        if(things.get(index) != null){
            return true;
        }
        return false;
    }


    public void addActor(Actor actor){
        if(actor instanceof Actor){
            addMonster(actor);
            System.out.println(actor.getName());
        }else if(actor instanceof Player){
            actor.setActualRoom(this);
        }
    }

    public Actor getFirstActor(){
        return monsters.get(0);
    }

    public ArrayList<Actor> getMonstersList(){
        return monsters;
    }

    public void addMonster(Actor monster){
        monsters.add(monster);
    }

    public void removeActor(Actor monster){
        monsters.remove(monster);
    }

    public void removeMonster(int index){
        monsters.remove(index);
    }

}
