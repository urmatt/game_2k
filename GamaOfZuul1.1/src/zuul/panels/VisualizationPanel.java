package zuul.panels;

import res.ResourseManager;
import zuul.welt.Actor;
import zuul.welt.Monster;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Панель для отображения иконок комнат и изображения монстров а так же вешей в нем
 */

public class VisualizationPanel extends JPanel implements MouseListener {

	HashMap<Actor, JLabel> monstersLabels = new HashMap<Actor, JLabel>();

    String mosterIconPath[] = new String []{"/res/monster1.gif", "/res/monster2.gif", "/res/monster3.gif", "/res/monster4.gif", "/res/monster5.gif"};
    int iconsCounter = 0;

    ImageIcon defIcon = ResourseManager.getIcon("/res/room1.png");
    JLabel roomLabel = new JLabel(defIcon);

	public VisualizationPanel() {
		panelInit();
	}

	public VisualizationPanel(Color color) {
		setBackground(color);
		panelInit();
	}
	
	public void setRoomIcon(ImageIcon icon){
		roomLabel.setIcon(icon);
	}

	private void panelInit(){
		setLayout(null);

        roomLabel.setBounds(5, 5, defIcon.getIconWidth(), defIcon.getIconHeight());
        add(roomLabel);
	}

    public void initMonsters(ArrayList<Actor> mList){
        iconsCounter = 0;
        removeAllMonsters();
        int x = 150, y = 5, wh = 64, hh = 64;
        for(int i = 0; i < mList.size();i++){
            JLabel monster = new JLabel();
            monster.setBounds(x, y, wh, hh);
            monster.setIcon(ResourseManager.getIcon(mosterIconPath[getNextIndex()]));
            monstersLabels.put(mList.get(i), monster);
            add(monster);
            repaint();
            x += 10; y += 10;
        }
    }

    public void removeAllMonsters(){
        for(Actor m: monstersLabels.keySet()){
            remove(monstersLabels.get(m));
        }
        monstersLabels.clear();
        repaint();
    }

    public void removeMonster(Actor monster){
        remove(monstersLabels.get(monster));
        monstersLabels.remove(monster);
        repaint();
    }

    private int getNextIndex(){
        if(iconsCounter >= mosterIconPath.length){
            iconsCounter = 1;
            return 0;
        }else{
            iconsCounter ++;
            return iconsCounter - 1;
        }
    }
	
	@Override
	public void mouseClicked(MouseEvent me) {;
//        remove(monsterLabel);
        revalidate();
        repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
