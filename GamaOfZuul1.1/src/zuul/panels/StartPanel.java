package zuul.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel{
	
	JButton startSpiel, ausgehen;
	
	Color hintergrund;
	
	public StartPanel() {
		init();
	}
	
	public StartPanel(Color hg) {
		hintergrund = hg;
		setBackground(hg);
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
}
