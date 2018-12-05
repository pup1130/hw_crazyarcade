package crazyarcade.character;

import javax.swing.*;
import java.awt.*;

public class Player {
    private Image img = new ImageIcon("src\\crazyarcade\\character\\player.png").getImage();
    private Image costume1=new ImageIcon("src\\crazyarcade\\character\\costume2.png").getImage();
    private double speed = 2;
    private int length = 1;
    private int wool_max=2;
    private int wool_cur=0;
    private int item_pizza_count=0;
    private int item_donut_count=0;
    private int item_wool_count=0;
    
    private double x, y;
    private boolean live=true;
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double d) {
        this.speed = d;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public int getWool_cur() {
		return wool_cur;
	}

	public void setWool_cur(int wool_cur) {
		this.wool_cur = wool_cur;
	}

	public int getWool_max() {
		return wool_max;
	}

	public void setWool_max(int wool_max) {
		this.wool_max = wool_max;
	}

	public int getItem_pizza_count() {
		return item_pizza_count;
	}

	public void setItem_pizza_count(int item_pizza_count) {
		this.item_pizza_count = item_pizza_count;
	}

	public int getItem_donut_count() {
		return item_donut_count;
	}

	public void setItem_donut_count(int item_donut_count) {
		this.item_donut_count = item_donut_count;
	}

	public int getItem_wool_count() {
		return item_wool_count;
	}

	public void setItem_wool_count(int item_wool_count) {
		this.item_wool_count = item_wool_count;
	}

	public Image getCostume1() {
		return costume1;
	}

	public void setCostume1(Image costume1) {
		this.costume1 = costume1;
	}


}
