package crazyarcade.gui;

import crazyarcade.Constant;
import crazyarcade.Game;
import crazyarcade.Keyboard;
import crazyarcade.exception.CAException;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel implements Constant {


    private Game game;
    private Frame frame;
    private ArcadeGamePanel arcadeGamePanel;
    private CardLayout cardLayout;

    MenuPanel(Frame frame, ArcadeGamePanel arcadeGamePanel, Keyboard keyboard) throws CAException {
        this.frame = frame;
        this.arcadeGamePanel = arcadeGamePanel;
        this.arcadeGamePanel.addKeyListener(keyboard);
        this.arcadeGamePanel.setFocusable(true);
        this.arcadeGamePanel.requestFocusInWindow();
        this.arcadeGamePanel.requestFocus();
//        Thread arcade = new Thread(this.arcadeGamePanel,"arcade");

//        cardLayout = new CardLayout();
//        JPanel panel = new JPanel();

        setBackground(Color.getHSBColor(0.084f, 0.1f, 0.99f));
        setLayout(null);
//        panel.setLayout(null);
//        arcadeGamePanel = new ArcadeGamePanel(frame, game);
//        add(arcadeGamePanel, "ArcadeGamePanel");

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

        arcadeButton.addActionListener(e -> {
            frame.getCards().show(getParent(), "ArcadeGamePanel");
            // game = new Game(frame);
            this.arcadeGamePanel.start();
//            arcade.start();
        });
        bossButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "개발 중임"));
        optionButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "개발 중임"));
        statusButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "개발 중임"));
        returnStartButton.addActionListener(e -> frame.getCards().show(getParent(), "StartPanel"));

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

//        panel.add(arcadeButton);
//        panel.add(bossButton);
//        panel.add(optionButton);
//        panel.add(statusButton);
//        panel.add(returnStartButton);
//
//        frame.add(panel);
    }
}
