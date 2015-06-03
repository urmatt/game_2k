package main;

import java.awt.Color;

import javax.swing.JFrame;

import zuul.panels.PanelListener;
import zuul.panels.StartPanel;
import Enums.PanelNavigation;
import Enums.PanelNavigation.Navigation;

public class SpielStart extends JFrame implements PanelListener{
	
	StartPanel startPanel;
	
	public SpielStart() {
		init();
	}

	public static void main(String[] args) {
		SpielStart start = new SpielStart();
	}
	
	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 300, 480);
		setTitle("Aidais Welt");
		startPanel = new StartPanel(Color.decode("#616161"));
		setContentPane(startPanel);
		setVisible(true);
	}

	@Override
	public void showPanel(Navigation navigation) {
		if(navigation == Navigation.START_PANEL){
			setContentPane(startPanel);
		}else if(navigation == Navigation.GAME_PANEL){
			
		}
	}

}
