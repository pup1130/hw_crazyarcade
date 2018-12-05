package crazyarcade.gui;

import crazyarcade.Constant;

import javax.swing.*;
import java.awt.*;


public class SignUpPanel extends JPanel implements Constant {

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

	SignUpPanel() {
		setVisible(true);
		setLayout(null);

		BackgroundPanel BackgroundPanel = new BackgroundPanel(
				new ImageIcon("src\\crazyarcade\\gui\\woolwool.png").getImage());
		add(BackgroundPanel);
	}

}
