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
                null, "2018 OOP Project\nCreated by\n이호준  & 정현우"," About",1));
        add(about);
        JMenuItem manual = new JMenuItem("Manual");
        manual.addActionListener(e -> JOptionPane.showMessageDialog(
        		null, "\n방향키로 캐릭터 이동\nSpacebar로 털실 설치\n\n\n<Arcade Mode>\n\n 상대방을 죽이면 다음단계로\n\n\n<Boss Mode>\n\n 보스를 죽이면 다음단계로","Manual",1));
        add(manual);
     }
}
