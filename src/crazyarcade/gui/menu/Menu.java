package crazyarcade.gui.menu;

import crazyarcade.gui.Frame;

import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.plaf.basic.BasicMenuBarUI;
import java.awt.*;

public class Menu extends JMenuBar {

    public static final Color color = Color.WHITE;

    public Menu(Frame frame) {
        add(new Game(frame));
        add(new Option(frame));
        add(new Help(frame));
    }

}
