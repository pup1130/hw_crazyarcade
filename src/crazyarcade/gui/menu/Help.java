package crazyarcade.gui.menu;

import crazyarcade.gui.Frame;

import javax.swing.*;

public class Help extends JMenu {

    public Frame frame;

    public Help(Frame frame) {
        super("Help");
        this.frame = frame;

        /*
        About
         */
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> JOptionPane.showMessageDialog(
                null, "2018 객지 수행평가\nCreated by\n이호준 & 정현우"));
        add(about);
    }
}
