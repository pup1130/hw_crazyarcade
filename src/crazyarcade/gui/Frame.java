package crazyarcade.gui;

import crazyarcade.Constant;
import crazyarcade.exception.CAException;
import crazyarcade.gui.menu.Menu;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame implements Constant {

    private StartPanel startPanel;
    private MenuPanel menuPanel;
    private ArcadeGamePanel arcadeGamePanel;
    private CardLayout cardLayout;

    public Frame() throws CAException {
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setTitle("Crazy Arcade " + GAME_VERSION);
        setJMenuBar(new Menu(this));
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
//        setBackground();

        cardLayout = new CardLayout();

        startPanel = new StartPanel(this);
        menuPanel = new MenuPanel(this);
        arcadeGamePanel = new ArcadeGamePanel(this);

        setLayout(cardLayout);
        cardLayout();
        cardLayout.show(getContentPane(), "StartPanel");
    }

    private void cardLayout() {
        add(startPanel, "StartPanel");
        add(menuPanel, "MenuPanel");
        //add(arcadeModeLoadingPanel,"ArcadeModeLoadingPanel");
        add(arcadeGamePanel, "ArcadeGamePanel");
    }

    public CardLayout getCards() {
        return cardLayout;
    }

}
