package main;

import Enums.ZuulEnums.Directions;
import Enums.ZuulEnums.Navigation;
import Enums.ZuulEnums.ThingType;
import zuul.panels.GamePanel;
import zuul.panels.PanelListener;
import zuul.panels.StartPanel;
import zuul.welt.Monster;
import zuul.welt.Player;
import zuul.welt.Room;
import zuul.welt.Thing;

import javax.swing.*;
import java.awt.*;

public class SpielStart extends JFrame implements PanelListener{
	
	StartPanel startPanel;
	GamePanel gamePanel;
	Player player;
	
	Thing medication, dazzle, dissolvent, hypnotic;
	
	Room startRoom, secondRoom, thridRoom, forthRoom;
	public SpielStart() {
		initZuulWorld();
		initGUI();
	}

	public static void main(String[] args) {
		SpielStart start = new SpielStart();
	}
	
	public void initGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 300, 480);
		setTitle("Aidais Welt");		
		
		startPanel = new StartPanel(this, Color.decode("#616161"));
		
		gamePanel = new GamePanel(this, player);
		
		setContentPane(startPanel);
		setVisible(true);
	}

	@Override
	public void showPanel(Navigation navigation) {
		if(navigation == Navigation.START_PANEL){
			setContentPane(startPanel);
		}else if(navigation == Navigation.GAME_PANEL){
			setBounds(100,100, 550, 480);
			setContentPane(gamePanel);
		}
		revalidate();		
	}
	
	private void initZuulWorld(){		
		startRoom = new Room("Start Room");
		secondRoom = new Room("secondRoom");
		thridRoom = new Room("thridRoom"); 
		forthRoom = new Room("forthRoom");
		
		startRoom.addExit(Directions.UP, secondRoom);
		secondRoom.addExit(Directions.RIGHT, thridRoom);
		secondRoom.addExit(Directions.DOWN, startRoom);//BACK TO START ROOM CONNECTION
		thridRoom.addExit(Directions.UP, forthRoom);
		thridRoom.addExit(Directions.LEFT, secondRoom);//BACK CONNECTION
		forthRoom.addExit(Directions.DOWN, thridRoom);//Back to 3-d Room
		
		medication = new Thing("Medikament", ThingType.MEDICATION);
        dazzle = new Thing("Blender", ThingType.DAZZLE);
        dissolvent = new Thing("Loesungsmittel", ThingType.DISSOLVENT);
        hypnotic = new Thing("Hypnoser", ThingType.HYPNOTIC);

		secondRoom.addThing(medication);
        thridRoom.addThing(dazzle);
        forthRoom.addThing(dissolvent);
        forthRoom.addThing(hypnotic);

        Monster monster1 = new Monster("" ,secondRoom);
		
		player = new Player("Aiperi", startRoom);
	}

}
