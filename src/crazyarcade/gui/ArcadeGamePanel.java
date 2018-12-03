package crazyarcade.gui;

import javax.swing.*;

import crazyarcade.Game;
import crazyarcade.exception.CAException;

import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;

public class ArcadeGamePanel extends JPanel {

    public static int[][] mapBlockNum = new int[15][15];
    boolean appear = true;
    boolean isWool = false;
    private Game game;
    private Frame frame;
    static int gameLevel = 0;


    ArcadeGamePanel(Frame frame) throws CAException {
        this.frame = frame;
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

//        game = new Game(frame);
//        this.add(game);

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

}
