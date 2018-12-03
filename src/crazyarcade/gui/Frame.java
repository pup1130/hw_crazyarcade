package crazyarcade.gui;

import crazyarcade.Constant;
import crazyarcade.Game;
import crazyarcade.Keyboard;
import crazyarcade.exception.CAException;
import crazyarcade.gui.menu.Menu;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame implements Constant {

    private StartPanel startPanel;
    private MenuPanel menuPanel;
    private ArcadeGamePanel arcadeGamePanel;
    private SignInOrUpPanel signinorupPanel;
    private SignUpPanel signupPanel;
    private SignInPanel signinPanel;
    static CardLayout cardLayout;
    private Game game;
    private Keyboard keyboard;

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
        keyboard = new Keyboard();
        addKeyListener(keyboard);
        game = new Game(this, keyboard);

        startPanel = new StartPanel(this);
        arcadeGamePanel = new ArcadeGamePanel(this, game, keyboard);
        menuPanel = new MenuPanel(this, arcadeGamePanel, keyboard);
        signinorupPanel = new SignInOrUpPanel();
        signinPanel = new SignInPanel();
        signupPanel = new SignUpPanel();
        

        setLayout(cardLayout);
        cardLayout();
        cardLayout.show(getContentPane(), "SignInOrUpPanel");
    }

    private void cardLayout() {
        add(startPanel, "StartPanel");
        add(menuPanel, "MenuPanel");
        //add(arcadeModeLoadingPanel,"ArcadeModeLoadingPanel");
        add(arcadeGamePanel, "ArcadeGamePanel");
        add(signinorupPanel, "SignInOrUpPanel");
        add(signinPanel, "SignInPanel");
        add(signupPanel, "SignUpPanel");
    }

    public CardLayout getCards() {
        return cardLayout;
    }

}
