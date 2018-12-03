package crazyarcade.character;

import javax.swing.*;
import java.awt.*;

public class Player {
    private Image img = new ImageIcon("src\\crazyarcade\\character\\Ket.png").getImage();
    private int speed = 2;
    private int x, y;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }


}
