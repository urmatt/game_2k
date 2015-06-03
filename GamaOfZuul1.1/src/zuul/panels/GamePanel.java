package zuul.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import main.StartPanelNotify;
import zuul.welt.Player;
import Enums.ZuulEnums.Directions;
import Enums.ZuulEnums.Navigation;

public class GamePanel extends JPanel implements StartPanelNotify{
	
	JButton left, up, right, down, takeThing, fight, back;
	JTextArea textArea;
	JScrollPane scrollPane, thingScroll, roomThingScroll;
	JList<String> dingsList = new JList<String>();
	JList<String> roomTingsList = new JList<String>();
	DefaultListModel<String> thingListModel = new DefaultListModel<String>();
	DefaultListModel<String> roomThingListModel = new DefaultListModel<String>();
	JLabel thingsListLabel;
	
	Player player;
	
	PanelListener listener;
	
	public GamePanel(PanelListener listener, Player player) {
		setListener(listener);
		setPlayer(player);
		init();
	}
	
	private void init() {
		setLayout(null);
		
		dingsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dingsList.setModel(thingListModel);
		
		thingScroll = new JScrollPane();
		thingScroll.setBounds(420, 270, 100, 100);
		thingScroll.setViewportView(dingsList);
		
		roomThingScroll = new JScrollPane();
		roomThingScroll.setBounds(420, 150, 100, 100);
		roomThingScroll.setViewportView(roomTingsList);
		
		
		add(thingScroll);
		add(roomThingScroll);
		
		left = new JButton("<");
		left.setBounds(10, 370, 44, 32);
		left.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textArea.append("\n"+player.moveTo(Directions.LEFT));
				updateRoomThingList();
			}
		});
		
		up = new JButton("^");
		up.setBounds(60, 350, 44, 32);
		up.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textArea.append("\n"+player.moveTo(Directions.UP));
				updateRoomThingList();
			}
		});
		
		right = new JButton(">");
		right.setBounds(110, 370, 44, 32);
		right.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {			
					textArea.append("\n"+player.moveTo(Directions.RIGHT));
					updateRoomThingList();
				
			}
		});
		
		down = new JButton("V");
		down.setBounds(60, 390, 44, 32);
		down.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textArea.append("\n"+player.moveTo(Directions.DOWN));
				updateRoomThingList();
			}
		});
		
		takeThing = new JButton("nehmen");
		takeThing.setBounds(420, 380, 100, 30);
		takeThing.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		add(takeThing);
		
		
		add(left);
		add(up);
		add(right);
		add(down);
		
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
	
	public void updateRoomThingList(){
		for(String names: player.getActualRoom().getThingNamesArray()){
			thingListModel.addElement(names);			
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
}
