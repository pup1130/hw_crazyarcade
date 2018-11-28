package crazyarcade.gui;

import crazyarcade.Constant;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel implements Constant {


    MenuPanel() {
        setBackground(Color.getHSBColor(0.084f, 0.1f, 0.99f));
        setVisible(true);
        this.setLayout(null);

        JButton arcadeButton = new JButton(new ImageIcon("src\\crazyarcade\\gui\\arcadeButton.png"));
        JButton bossButton = new JButton("bossButton");
        JButton optionButton = new JButton("optionButton");
        JButton statusButton = new JButton("statusButton");
        JButton returnStartButton = new JButton(new ImageIcon("src\\crazyarcade\\gui\\returnStart.png"));

        arcadeButton.setBorderPainted(false);
        arcadeButton.setContentAreaFilled(false);
        arcadeButton.setFocusPainted(false);

//        bossButton.setBorderPainted(false);
//        bossButton.setContentAreaFilled(false);
//        bossButton.setFocusPainted(false);
//
//        optionButton.setBorderPainted(false);
//        optionButton.setContentAreaFilled(false);
//        optionButton.setFocusPainted(false);
//
//        statusButton.setBorderPainted(false);
//        statusButton.setContentAreaFilled(false);
//        statusButton.setFocusPainted(false);
//
//        returnStartButton.setBorderPainted(false);
//        returnStartButton.setContentAreaFilled(false);
//        returnStartButton.setFocusPainted(false);

        arcadeButton.addActionListener(e -> Frame.cards.show(getParent(), "ArcadeGamePanel"));
        bossButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "개발 중임"));
        optionButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "개발 중임"));
        statusButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "개발 중임"));
        returnStartButton.addActionListener(e -> Frame.cards.show(getParent(), "StartPanel"));

        arcadeButton.setBounds(255, 50, MENU_BUTTON_LENGTH, MENU_BUTTON_LENGTH);
        bossButton.setBounds(505, 50, MENU_BUTTON_LENGTH, MENU_BUTTON_LENGTH);
        optionButton.setBounds(255, 300, MENU_BUTTON_LENGTH, MENU_BUTTON_LENGTH);
        statusButton.setBounds(505, 300, MENU_BUTTON_LENGTH, MENU_BUTTON_LENGTH);
        returnStartButton.setBounds(750, 450, RETURN_START_BUTTON_LENGTH, RETURN_START_BUTTON_LENGTH);

        add(arcadeButton);
        add(bossButton);
        add(optionButton);
        add(statusButton);
        add(returnStartButton);

    }
}
