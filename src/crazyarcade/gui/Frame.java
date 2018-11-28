package crazyarcade.gui;

import crazyarcade.Constant;
import crazyarcade.Game;
import crazyarcade.gui.menu.Menu;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame implements Constant {

    public Game game;

    static CardLayout cards = new CardLayout();
    static StartPanel startPanel = new StartPanel();
    static MenuPanel menuPanel = new MenuPanel();
    //    static ArcadeModeLoadingPanel arcadeModeLoadingPanel = new ArcadeModeLoadingPanel();
    static ArcadeGamePanel arcadeGamePanel = new ArcadeGamePanel();

    public Frame() {

        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setTitle("Crazy Arcade " + GAME_VERSION);
        setJMenuBar(new Menu(this));
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
//        setBackground();

        setLayout(cards);
        cardLayout();
        cards.show(getContentPane(), "StartPanel");
    }

    private void cardLayout() {
        add(startPanel, "StartPanel");
        add(menuPanel, "MenuPanel");
        //add(arcadeModeLoadingPanel,"ArcadeModeLoadingPanel");
        add(arcadeGamePanel, "ArcadeGamePanel");
    }

}
