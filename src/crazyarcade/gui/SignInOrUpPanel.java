package crazyarcade.gui;

import crazyarcade.Constant;

import javax.swing.*;
import java.awt.*;


public class SignInOrUpPanel extends JPanel implements Constant {

    class SignBackgroundPanel extends JPanel{
        private Image image;

        SignBackgroundPanel(Image image) {
            this.image = image;
            setSize(new Dimension(image.getWidth(null), image.getHeight(null)));
            setLayout(null);
        }

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, null);
        }
    }

    SignInOrUpPanel() {
        setVisible(true);
        setLayout(null);

        SignBackgroundPanel signBackgroundPanel = new SignBackgroundPanel(
                new ImageIcon("src\\crazyarcade\\gui\\startBackground.png").getImage());
        add(signBackgroundPanel);

        JButton signinButton = new JButton(new ImageIcon("src\\crazyarcade\\gui\\signin.png"));
        signinButton.setBorderPainted(false);
        signinButton.setContentAreaFilled(false);
        signinButton.setFocusPainted(false);

        signinButton.addActionListener(e -> Frame.cards.show(getParent(), "SignInPanel"));

        signinButton.setBounds(SCREEN_WIDTH / 2 - START_BUTTON_WIDTH / 2, (SCREEN_HEIGHT - 255) / 2 - START_BUTTON_HEIGHT / 2, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);

        signBackgroundPanel.add(signinButton);

        JButton signupButton = new JButton(new ImageIcon("src\\crazyarcade\\gui\\signup.png"));
        signupButton.setBorderPainted(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setFocusPainted(false);

        signupButton.addActionListener(e -> Frame.cards.show(getParent(), "SignUpPanel"));

        signupButton.setBounds(SCREEN_WIDTH / 2 - START_BUTTON_WIDTH / 2, (SCREEN_HEIGHT + 145) / 2 - START_BUTTON_HEIGHT / 2, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);

        signBackgroundPanel.add(signupButton);
    }

}
