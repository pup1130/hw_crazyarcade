package crazyarcade.gui;

import crazyarcade.Constant;

import javax.swing.*;
import java.awt.*;


public class StartPanel extends JPanel implements Constant {

    private Frame frame;

    class StartBackgroundPanel extends JPanel {
        private Image image;

        StartBackgroundPanel(Image image) {
            this.image = image;
            setSize(new Dimension(image.getWidth(null), image.getHeight(null)));
            setLayout(null);
        }

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, null);
        }
    }


    StartPanel(Frame frame) {
        this.frame = frame;
        setVisible(true);
        setLayout(null);

        StartBackgroundPanel startBackgroundPanel = new StartBackgroundPanel(
                new ImageIcon("src\\crazyarcade\\gui\\startBackground.png").getImage());
        add(startBackgroundPanel);

        JButton startButton = new JButton(new ImageIcon("src\\crazyarcade\\gui\\start.png"));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);

        startButton.addActionListener(e -> frame.getCards().show(getParent(), "MenuPanel"));

        startButton.setBounds(SCREEN_WIDTH / 2 - START_BUTTON_WIDTH / 2, (SCREEN_HEIGHT - 55) / 2 - START_BUTTON_HEIGHT / 2, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);

        startBackgroundPanel.add(startButton);
    }

}
