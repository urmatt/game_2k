package zuul.panels;

import Enums.ZuulEnums;
import Enums.ZuulEnums.Directions;
import Enums.ZuulEnums.Navigation;
import main.StartPanelNotify;
import res.ResourseManager;
import zuul.welt.Actor;
import zuul.welt.Player;
import zuul.welt.Room;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Random;

public class GamePanel extends JPanel implements StartPanelNotify{

    JButton left, up, right, down, use, fight, back, take;
	JTextArea textArea;
	JScrollPane scrollPane, thingScroll, roomThingScroll;
	JList<String> thingsList = new JList<String>();
	JList<String> roomThingsList = new JList<String>();
	DefaultListModel<String> thingListModel = new DefaultListModel<String>();
	DefaultListModel<String> roomThingListModel = new DefaultListModel<String>();
	JLabel thingsListLabel;
    JLabel gameMap;
	PanelListener listener;

    
    VisualizationPanel visualPanel;

    int iconCounter = 0;

    ImageIcon hypnozer = ResourseManager.getIcon("/res/hypnoze.gif");
    ImageIcon dath = ResourseManager.getIcon("/res/dath.gif");
    ImageIcon dazzler = ResourseManager.getIcon("/res/dazzler.gif");
    ImageIcon medicament = ResourseManager.getIcon("/res/medicament.gif");
    ImageIcon dissolver = ResourseManager.getIcon("/res/dissolvent.gif");

    String roomIconPath[] = new String[]{"/res/room1.png", "/res/room2.png", "/res/room3.png", "/res/room4.png", "/res/room5.png"};


    HashMap<Room, ImageIcon> roomIcons = new HashMap<Room, ImageIcon>();

	Player player;
    Actor selectedActor = null;

	public GamePanel(PanelListener listener, Player player) {
		setListener(listener);
		setPlayer(player);
		init();
		visualPanelInit();
	}
	
	private void init() {
		setLayout(null);

        GameListener gl=new GameListener();

        gameMap = new JLabel(ResourseManager.getIcon("/res/game_map.png"));
        gameMap.setBounds(280, 20, 130, 130);
        add(gameMap);

        //List for Player things
		thingsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		thingsList.setModel(thingListModel);

		thingScroll = new JScrollPane();
		thingScroll.setBounds(420, 270, 100, 100);
		thingScroll.setViewportView(thingsList);
        add(thingScroll);

        //List for Things in the Room
        roomThingsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomThingsList.setModel(roomThingListModel);

		roomThingScroll = new JScrollPane();
		roomThingScroll.setBounds(420, 100, 100, 100);
		roomThingScroll.setViewportView(roomThingsList);
		add(roomThingScroll);
		
		left = new JButton("<");
		left.setBounds(10, 370, 44, 32);
        left.addActionListener(gl);
		
		up = new JButton("^");
		up.setBounds(60, 350, 44, 32);
		up.addActionListener(gl);
		
		right = new JButton(">");
		right.setBounds(110, 370, 44, 32);
		right.addActionListener(gl);
		
		down = new JButton("V");
		down.setBounds(60, 390, 44, 32);
		down.addActionListener(gl);
		
		use = new JButton("benutzen");
        use.setActionCommand(ZuulEnums.Commands.USE.toString());
		use.setBounds(420, 380, 100, 30);
		use.addActionListener(gl);

        take = new JButton("nahmen");
        take.setActionCommand(ZuulEnums.Commands.TACKE.toString());
        take.setBounds(420, 210, 100, 30);
        take.addActionListener(gl);

        back = new JButton("zueruek");
        back.setActionCommand(ZuulEnums.Commands.BACK.toString());
        back.setBounds(420, 10, 100, 30);
        back.addActionListener(gl);
		
		add(left);
		add(up);
		add(right);
		add(down);

        add(use);
        add(take);
        add(back);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		scrollPane.setBounds(10, 270, 400, 70);
		scrollPane.setViewportView(textArea);
		
		add(scrollPane);
		
		thingsListLabel = new JLabel("Your thins");
		thingsListLabel.setBounds(420, 251, 70, 14);
		add(thingsListLabel);
	}

	private void visualPanelInit(){
		visualPanel = new VisualizationPanel(Color.decode("#757575"));
		visualPanel.setBounds(20, 20, 250, 200);
        visualPanel.setRoomIcon(getRoomIcon(player.getActualRoom()));
		add(visualPanel);
	}


	public void updateRoomThingList(){
        roomThingListModel.clear();
		for(String names: player.getActualRoom().getThingNamesArray()){
			roomThingListModel.addElement(names);
		}
	}

    public void updatePlayerThingsList(){
        thingListModel.clear();
        for(String names: player.getThingsNamesArray()){
            thingListModel.addElement(names);
        }
    }

    private class GameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if(cmd.equals("<")) {
                textArea.append("\n" + player.moveTo(Directions.LEFT));
            }else if(cmd.equals("^")){
                textArea.append("\n" + player.moveTo(Directions.UP));
            }else if(cmd.equals(">")){
                textArea.append("\n" + player.moveTo(Directions.RIGHT));
            }else if(cmd.equals("V") || cmd.equals("v")){
                textArea.append("\n" + player.moveTo(Directions.DOWN));
            }else if(cmd.equals(ZuulEnums.Commands.TACKE.toString())){
                int index = roomThingsList.getSelectedIndex();
                if(player.thakeThing(index)) {
                    textArea.append("\nIch habe " + roomThingsList.getSelectedValue()+ " genommen");
                    updatePlayerThingsList();
                }else if(roomThingListModel.getSize() <= 0){
                    textArea.append("\nKeine Dinge zu naehmen.");
                }else{
                    textArea.append("\nWhaele zu erst etwas.");
                }
            }else if(cmd.equals(ZuulEnums.Commands.USE.toString())) {
                if(thingsList.getSelectedIndex() != -1) {
                    textArea.append("\n" + player.useThing(player.getThing(thingsList.getSelectedIndex())));
                    updatePlayerThingsList();
                }else{
                    textArea.append("\nWaehle etwas ! ! !");
                }
//                if(player.getThing(thingsList.getSelectedIndex()).getType() == ZuulEnums.ThingType.MEDICATION){
//                    textArea.append("\nMEDIKAMENT");
//                }
            }else if(cmd.equals(ZuulEnums.Commands.BACK.toString())){
                listener.showPanel(Navigation.START_PANEL);
            }
            updateRoomThingList();
            visualPanel.initMonsters(player.getActualRoom().getMonstersList());
            visualPanel.setRoomIcon(getRoomIcon(player.getActualRoom()));
        }
    }
	
	public void addThing(String thName){
		thingListModel.addElement(thName);
	}

    private ImageIcon getRoomIcon(Room room){
            if(roomIcons.containsKey(room)){
                return roomIcons.get(room);
            }else{
                ImageIcon ico = ResourseManager.getIcon(roomIconPath[getNextCount()]);
                roomIcons.put(room, ico);
                return ico;
            }
    }

    int getNextCount(){
        if(iconCounter < roomIconPath.length){
            iconCounter++;
            return iconCounter - 1;
        }else{
            iconCounter=0;
            return iconCounter;
        }
    }

	@Override
	public void setListener(PanelListener listener) {
		this.listener = listener;
	}

	@Override
	public void notifyListener(Navigation toPanel) {
		listener.showPanel(toPanel);
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
}
