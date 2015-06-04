package zuul.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VisualizationPanel extends JPanel implements MouseListener {

	HashMap<Rectangle, JLabel> forDrawing = new HashMap<Rectangle, JLabel>();

	public VisualizationPanel() {
		panelInit();
	}

	public VisualizationPanel(Color color) {
		setBackground(color);
		panelInit();
	}

	public void addImage(JLabel label, Rectangle rect) {
		label.setBounds(rect);
		forDrawing.put(rect, label);
		add(label);
	}
	
	public void addLabel(JLabel lab){
		
	}
	
	public void removeImage(JLabel icon){
		for(Rectangle rec: forDrawing.keySet()){
			if(forDrawing.get(rec) == icon){
				forDrawing.remove(rec);
			}
		}
	}

	private void panelInit(){
		setLayout(null);		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
