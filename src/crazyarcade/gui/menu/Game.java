package crazyarcade.gui.menu;

import crazyarcade.gui.Frame;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Game extends JMenu {

    public Frame frame;

    public Game(Frame frame) {
        super("Game");
        this.frame = frame;

        //* Exit
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        add(exit);
    }

/*
    class MenuActionListener implements ActionListener {

        public Frame _frame;
        public MenuActionListener(Frame frame) {
            _frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getActionCommand().equals("New Game")) {
                _frame.newGame();
            }

        }
    }
*/
}