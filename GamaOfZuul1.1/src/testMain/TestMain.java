package testMain;

import Enums.ZuulEnums.Directions;
import zuul.welt.Room;

public class TestMain {

	public static void main(String[] args) {
		Room r1= new Room("First Room");
		Room r2= new Room("Second Room");
		Room r3= new Room("Thrid Room");
		Room r4= new Room("Forth Room");
		
		r1.addExit(Directions.LEFT, r2);
		r1.addExit(Directions.UP, r3);
		r1.addExit(Directions.RIGHT, r4);
		
		for(Directions d: r1.getExitsList()){
			System.out.println(d);
		}
	}

}
