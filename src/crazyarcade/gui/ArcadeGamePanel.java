package crazyarcade.gui;

import crazyarcade.Game;
import crazyarcade.Keyboard;
import crazyarcade.character.Player;
import crazyarcade.exception.CAException;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ArcadeGamePanel extends JPanel implements Runnable {

    public static int[][] mapBlockNum = new int[15][15];

    boolean appear = true;
    boolean isWool = false;
    private Frame frame;
    private Game game;
    public static Keyboard input;
    static int gameLevel = 0;
    private Image buffimg;
    private Graphics gc;
    private int cnt;
//    private Player player = new Player();


    ArcadeGamePanel(Frame frame, Game game) throws CAException {
        this.frame = frame;
        this.game = game;
        setFocusable(true);
        requestFocusInWindow();
        setBackground(Color.BLUE);
        setVisible(true);
        setLayout(null);


        //
        try {
            readBackgroundInfo(new FileReader("src\\crazyarcade\\graphic\\mapFile\\map1.txt"));
            gameLevel++;
            // g.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        frame.add(new Game(frame));

//        game.start();
//
//        try {
//            readBackgroundInfo(new FileReader("src\\crazyarcade\\graphic\\mapFile\\map2.txt"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        drawBackground();
    }

    private void readBackgroundInfo(FileReader mapFileReader) throws IOException {
        String str = "";
        StringTokenizer stringTokenizer;
        BufferedReader bufferedReader = new BufferedReader(mapFileReader);

        for (int i = 0; i < 15; i++) {
            str = bufferedReader.readLine();
            stringTokenizer = new StringTokenizer(str);

            for (int j = 0; j < 15; j++) {
                mapBlockNum[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
    }

    public void start() {
        input = new Keyboard();
        addKeyListener(input);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setBackground(Color.RED);
        game.init();


    }

    @Override
    public void paint(Graphics g) {
        buffimg = createImage(542, 542);
        gc = buffimg.getGraphics();
        update(g);
    }

    @Override
    public void update(Graphics g) {
        DrawImg();
        g.drawImage(buffimg, 0, 0, this);
    }

    private void DrawImg() {
        gc.setColor(Color.GRAY);
        gc.drawRect(0, 0, 542, 542);
        gc.setClip(0, 0, 36, 24);
        gc.drawImage(game.player.getImg(), game.player.getX(), game.player.getY(), this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                game.keyProcess();
                repaint();

                Thread.sleep(20);
                cnt++;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
