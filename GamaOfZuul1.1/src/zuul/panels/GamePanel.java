package zuul.panels;

import Enums.ZuulEnums;
import Enums.ZuulEnums.Directions;
import Enums.ZuulEnums.Navigation;
import main.StartPanelNotify;
import zuul.welt.Actor;
import zuul.welt.Player;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Random;

public class GamePanel extends JPanel implements StartPanelNotify, MouseListener{

    JButton left, up, right, down, use, fight, back, take;
	JTextArea textArea;
	JScrollPane scrollPane, thingScroll, roomThingScroll;
	JList<String> thingsList = new JList<String>();
	JList<String> roomThingsList = new JList<String>();
	DefaultListModel<String> thingListModel = new DefaultListModel<String>();
	DefaultListModel<String> roomThingListModel = new DefaultListModel<String>();
	JLabel thingsListLabel;
	PanelListener listener;
    JLabel roomLabelIcon;
    
    VisualizationPanel visualPanel;

    ImageIcon hypnozer = new ImageIcon("./res/hypnoze.gif");
    ImageIcon dath = new ImageIcon("res/dath.gif");
    ImageIcon dazzler = new ImageIcon("res/dazzler.gif");
    ImageIcon room1 = new ImageIcon(GamePanel.class.getResource("/res/room1.png"));
    ImageIcon medicament = new ImageIcon("res/medicament.gif");
    ImageIcon dissolver = new ImageIcon("res/dissolvent.gif");

    String roomIconPath[] = new String[]{"res/room1.png", "res/room2.png", "res/room3.png", "res/room4.png", "res/room5.png"};
    String mosterImageIconPath[] = new String []{"res/monster1.gif", "res/monster2.gif", "res/monster3.gif", "res/monster4.gif", "res/monster5.gif"};
    int coordinates[][] = new int[][]{{10, 10}, {10, 40}, {10, 60}, {40, 30}, {10, 10}};
    HashMap<Actor, JLabel> monstersWithIcons = new HashMap<Actor, JLabel>();

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

        roomLabelIcon = new JLabel("RAUM");
        roomLabelIcon.setIcon(dazzler);
        roomLabelIcon.setBounds(10, 10, 300, 300);

        add(roomLabelIcon);


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
		
		add(left);
		add(up);
		add(right);
		add(down);

        add(use);
        add(take);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		scrollPane.setBounds(10, 270, 400, 70);
		scrollPane.setViewportView(textArea);
		
		add(scrollPane);
		
		thingsListLabel = new JLabel("Your thins");
		thingsListLabel.setBounds(420, 251, 70, 14);
		add(thingsListLabel);
        updateMonstersIcons();
	}

//    private void initMonsterIconList(){
//        for(Actor act: player.getActualRoom().getMonstersList()){
//
//        }
//    }

	private void visualPanelInit(){
		visualPanel = new VisualizationPanel(Color.decode("#757575"));
		visualPanel.setBounds(20, 20, 250, 200);
		JLabel lab = new JLabel();
		lab.setIcon(room1);
		visualPanel.addImage(lab, new Rectangle(0,0,100,100));
		add(visualPanel);
	}
	
    public void updateMonstersIcons(){
        Random random = new Random();
        for(Actor ac : player.getActualRoom().getMonstersList()){
            int a = random.nextInt(mosterImageIconPath.length-1);

            JLabel mlab = new JLabel();
            mlab.setBounds(coordinates[a][0], coordinates[a][1], 50, 50);
            mlab.setIcon(new ImageIcon(mosterImageIconPath[random.nextInt(mosterImageIconPath.length-1)]));
            monstersWithIcons.put(ac, mlab);
            add(mlab);
        }
    }

    private void removeActor(Actor act){
        player.getActualRoom().removeMonster(act);
        remove(monstersWithIcons.get(act));
        revalidate();
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
                if(player.getThing(thingsList.getSelectedIndex()).getType() == ZuulEnums.ThingType.MEDICATION){
                    textArea.append("\nMEDIKAMENT");
                }
                if(selectedActor != null) {
                    player.useThing(thingsList.getSelectedIndex(), selectedActor);
                    removeActor(selectedActor);
                }
            }
            updateRoomThingList();
            updateMonstersIcons();
        }
    }
	
	public void addThing(String thName){
		thingListModel.addElement(thName);
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


    @Override
    public void mouseClicked(MouseEvent e) {

        for(Actor sAc: monstersWithIcons.keySet()){
            if (monstersWithIcons.get(sAc).equals(e.getComponent())){
                selectedActor = sAc;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
