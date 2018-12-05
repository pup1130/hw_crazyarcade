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
                null, "2018 OOP Project\nCreated by\n��ȣ�� & ������"," About",1));
        add(about);
        JMenuItem manual = new JMenuItem("Manual");
        manual.addActionListener(e -> JOptionPane.showMessageDialog(
        		null, "\n����Ű�� ĳ���� �̵�\nSpacebar�� �н� ��ġ\n\n\n<Arcade Mode>\n\n ������ ���̸� �¸�\n\n\n<Boss Mode>\n\n ������ ���̸� �¸�","Manual",1));
        add(manual);
        JMenuItem items = new JMenuItem("items");
        items.addActionListener(e-> JOptionPane.showMessageDialog(
        		null, "\nItems\n\n\npizza\n\nĳ���� �ӵ� ����\n\n\nwool\n\n��ġ ������ �н� �� ����\n\n\ndonut\n\n������ �Ÿ� ����", "Items", 1));
        add(items);
     }
}
