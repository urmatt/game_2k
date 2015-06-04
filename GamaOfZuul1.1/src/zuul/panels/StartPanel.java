package zuul.panels;

import Enums.ZuulEnums.Navigation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel{
	
	JButton startSpiel, ausgehen;
	
	PanelListener listener;
	
	Color background;
	
	public StartPanel(PanelListener listener) {
		setListener(listener);
		init();
	}
	
	public StartPanel(PanelListener listener ,Color bg) {
		setListener(listener);
		background = bg;
		setBackground(bg);
		init();
	}
	
	public void init(){
		setLayout(null);
		startSpiel = new JButton("Spielen");
		startSpiel.setBounds(100, 150, 100, 50);
		
		ausgehen = new JButton("Zum Ausgang");
		ausgehen.setBounds(100, 250, 100, 50);
		
		startSpiel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listener.showPanel(Navigation.GAME_PANEL);
			}
		});
		
		ausgehen.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(startSpiel);
		add(ausgehen);
	}
	
	public void setListener(PanelListener listener){
		this.listener = listener;
	}
}
