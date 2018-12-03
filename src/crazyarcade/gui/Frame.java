package crazyarcade.gui;

import crazyarcade.Constant;
import crazyarcade.Game;
import crazyarcade.exception.CAException;
import crazyarcade.gui.menu.Menu;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame implements Constant {

    private StartPanel startPanel;
    private MenuPanel menuPanel;
    private ArcadeGamePanel arcadeGamePanel;
    private CardLayout cardLayout;
    private Game game;

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
        game = new Game(this);

        startPanel = new StartPanel(this);
        arcadeGamePanel = new ArcadeGamePanel(this, game);
        menuPanel = new MenuPanel(this, arcadeGamePanel);

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
