package crazyarcade.character;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

public class Item {
    public static Image pizza = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\character\\pizza.png");
    public static Image donut = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\character\\donut.png");
    public static Image wool = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\character\\wool item.png");

    public static int giveItem() {
        Random random = new Random();
        return random.nextInt(15);
        //a==1 pizza
        //a==2 donut
        //a==3 wool
        //level에 따라 확률 조정 가능
    }
}