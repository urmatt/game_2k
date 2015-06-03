package zuul.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;

import zuul.welt.Player;
import zuul.welt.Room;
import main.StartPanelNotify;
import Enums.PanelNavigation.Directions;
import Enums.PanelNavigation.Navigation;

public class GamePanel extends JPanel implements StartPanelNotify{
	
	JButton left, up, right, down, takeThing, fight, back;
	
	Player player;
	
	PanelListener listener;
	
	public GamePanel(PanelListener listener, Player player) {
		this.listener = listener;
	}
	
	private void init() {
		setLayout(null);
		left = new JButton("nach Links");
		left.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
	}

	@Override
	public void setListener(PanelListener listener) {
		this.listener = listener;
	}

	@Override
	public void notifyListener(Navigation toPanel) {
		listener.showPanel(toPanel);
	}
}
