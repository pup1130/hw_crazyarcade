package crazyarcade.gui;

import javax.swing.*;
import java.awt.*;

public class ArcadeModeLoadingPanel extends JPanel {

    ArcadeModeLoadingBackgroundPanel arcadeModeLoading1 = new ArcadeModeLoadingBackgroundPanel(
            new ImageIcon("src\\crazyarcade\\gui\\ArcadeModeLoading1.png").getImage()
    );
    ArcadeModeLoadingBackgroundPanel arcadeModeLoading2 = new ArcadeModeLoadingBackgroundPanel(
            new ImageIcon("src\\crazyarcade\\gui\\ArcadeModeLoading2.png").getImage()
    );
    ArcadeModeLoadingBackgroundPanel arcadeModeLoading3 = new ArcadeModeLoadingBackgroundPanel(
            new ImageIcon("src\\crazyarcade\\gui\\ArcadeModeLoading3.png").getImage()
    );

    static CardLayout cards = new CardLayout();

    class ArcadeModeLoadingBackgroundPanel extends JPanel {
        private Image image;

        ArcadeModeLoadingBackgroundPanel(Image image) {
            this.image = image;
            setSize(new Dimension(image.getWidth(null), image.getHeight(null)));
            setLayout(null);
        }

        protected void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, null);
        }
    }

    ArcadeModeLoadingPanel() {

        setVisible(true);
        setLayout(cards);
        cardLayout();
        cards.show(this, "ArcadeModeLoading1");

//
//        Timer timer = new Timer();
//        TimerTask timerTask1 = new TimerTask() {
//            @Override
//            public void run() {
//                cards.show(ArcadeModeLoadingPanel.this, "ArcadeModeLoading1");
//            }
//        };
//
//        TimerTask timerTask2 = new TimerTask() {
//            @Override
//            public void run() {
//                cards.show(ArcadeModeLoadingPanel.this, "ArcadeModeLoading2");
//            }
//        };
//
//        TimerTask timerTask3 = new TimerTask() {
//            @Override
//            public void run() {
//                cards.show(ArcadeModeLoadingPanel.this, "ArcadeModeLoading3");
//            }
//        };
//
//        timer.schedule(timerTask2, 5000);
//        timer.schedule(timerTask3, 10000);
//        timer.schedule(timerTask1, 1500);
//        timer.schedule(timerTask2, 2000);
//        timer.schedule(timerTask3, 2500);


    }

    private void cardLayout() {
        add(arcadeModeLoading1, "ArcadeModeLoading1");
        add(arcadeModeLoading2, "ArcadeModeLoading2");
        add(arcadeModeLoading3, "ArcadeModeLoading3");
    }
}
