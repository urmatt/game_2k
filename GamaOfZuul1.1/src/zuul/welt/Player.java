package zuul.welt;

public class Player {
	String name;
	
	Room actualRoom;
	
	
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
	
	
}
