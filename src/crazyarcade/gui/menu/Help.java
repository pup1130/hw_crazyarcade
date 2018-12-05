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
                null, "2018 OOP Project\nCreated by\n이호준 & 정현우", " About", 1));
        add(about);

        /*
        Manual
         */
        JMenuItem manual = new JMenuItem("Manual");
        manual.addActionListener(e -> JOptionPane.showMessageDialog(
                null, "\n방향키로 캐릭터 이동\nSpacebar로 털실 설치\n\n\n<Arcade Mode>\n\n 상대방을 죽이면 승리\n\n\n<Boss Mode>\n\n 보스를 죽이면 승리", "Manual", 1));
        add(manual);

        /*
        Items
         */
        JMenuItem items = new JMenuItem("items");
        items.addActionListener(e-> JOptionPane.showMessageDialog(
                null, "\nItems\n\n\npizza\n\n캐릭터 속도 증가\n\n\nwool\n\n설치 가능한 털실 수 증가\n\n\ndonut\n\n터지는 거리 증가", "Items", 1));
        add(items);
     }
}
