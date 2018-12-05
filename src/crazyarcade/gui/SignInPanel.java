package crazyarcade.gui;

import crazyarcade.Constant;

import javax.swing.*;
import java.awt.*;


public class SignInPanel extends JPanel implements Constant {

    class BackgroundPanel extends JPanel{
        private Image image;

        BackgroundPanel(Image image) {
            this.image = image;
            setSize(new Dimension(image.getWidth(null), image.getHeight(null)));
            setLayout(null);
        }

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, null);
        }
    }

    SignInPanel() {
        setVisible(true);
        setLayout(null);

        BackgroundPanel BackgroundPanel = new BackgroundPanel(
                new ImageIcon("src\\crazyarcade\\gui\\startBackground.png").getImage());
        add(BackgroundPanel);


        JButton okButton = new JButton(new ImageIcon("src\\crazyarcade\\gui\\ok.png"));
        okButton.setBorderPainted(false);
        okButton.setContentAreaFilled(false);
        okButton.setFocusPainted(false);

        okButton.addActionListener(e -> Frame.cardLayout.show(getParent(), "StartPanel"));

        okButton.setBounds(SCREEN_WIDTH / 2 - START_BUTTON_WIDTH / 2, (SCREEN_HEIGHT - 55) / 2 - START_BUTTON_HEIGHT / 2, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);

        BackgroundPanel.add(okButton);
    }

}
