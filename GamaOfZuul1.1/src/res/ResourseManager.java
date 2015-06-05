package res;

import javax.swing.*;

/**
 * Created by Urma on 4-Jun 15.
 */
public class ResourseManager {
    public static ImageIcon getIcon(String path){
        return new ImageIcon(ResourseManager.class.getResource(path));
    }
}
