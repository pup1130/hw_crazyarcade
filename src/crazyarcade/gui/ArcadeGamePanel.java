package crazyarcade.gui;

import crazyarcade.Constant;
import crazyarcade.Game;
import crazyarcade.Keyboard;
import crazyarcade.exception.CAException;
import crazyarcade.graphic.mapBlock.Block;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ArcadeGamePanel extends JPanel implements Constant, Runnable {

    public static int[][] mapBlockNum = new int[15][15];
    int dir[][]= {{1,0},{0,1},{-1,0},{0,-1}};
    boolean appear = true;
    boolean isWool = false;
    private Frame frame;
    private Game game;
    private Keyboard keyboard;
    public static Keyboard input;
    static int gameLevel = 0;
    private Image buffimg;
    private Graphics gc;
    private int cnt;
    private Thread thread;
//    private Player player = new Player();


    ArcadeGamePanel(Frame frame, Game game, Keyboard keyboard) throws CAException {
        this.frame = frame;
        this.game = game;
        this.keyboard = keyboard;
//        setFocusable(true);
//        requestFocusInWindow();
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
//        input = new Keyboard();
//        frame.addKeyListener(input);
        setFocusable(true);
        requestFocus();
        requestFocusInWindow();
        thread = new Thread(this);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

        gc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        for (int i = 0; i < BLOCK_COUNT; i++) {
            for (int j = 0; j < BLOCK_COUNT; j++) {
                if (mapBlockNum[i][j] != 0) {
                    if (game.getMapBlock(i, j).isAppear()) {
                        gc.drawImage(game.getMapBlock(i, j).getBlockImage(), i * ONE_BLOCK_LENGTH, j * ONE_BLOCK_LENGTH, this);
                    }
                }
            }
        }
        for(int i=0;i<BLOCK_COUNT;i++) {
        	for(int j=0;j<BLOCK_COUNT;j++) {
        		if(game.getMapBlock(i, j).isWool()) {
        			gc.drawImage(game.getMapBlock(i, j).getBlockImage(), i*ONE_BLOCK_LENGTH, j*ONE_BLOCK_LENGTH,this);
        			if(game.getWoolCount(i, j)<WOOL_LENGTH) {
        			game.setWoolCount(i, j, game.getWoolCount(i, j)+1);
        			}
        			else {
        				game.setWoolCount(i, j, 0);
        				game.getMapBlock(i, j).setWool(false);
        				game.getMapBlock(i, j).setExploded(true);
        				game.setMapBlock(i, j, 257);
        				for(int di=0;di<4;di++) {
        					for(int k=1;k<=game.player.getLength();k++) {
        						if(i+k*dir[di][0]>=0&&i+k*dir[di][0]<BLOCK_COUNT&&j+k*dir[di][1]>=0&&j+k*dir[di][1]<BLOCK_COUNT) {
        					if(game.getMapBlock(i+k*dir[di][0],j+k*dir[di][1]).getBlockNumber()==0) {
        						game.getMapBlock(i+k*dir[di][0],j+k*dir[di][1]).setExploded(true);
        						game.setMapBlock(i+k*dir[di][0],j+k*dir[di][1],257);
        						
        					}
        					else {
        						break;
        					}
        						}
        					}
        				}
        			}
        		}
        	}
        }
        for(int i=0;i<BLOCK_COUNT;i++) {
        	for(int j=0;j<BLOCK_COUNT;j++) {
        		if(game.getMapBlock(i, j).isExploded()) {
        			gc.drawImage(game.getMapBlock(i, j).getBlockImage(), i*ONE_BLOCK_LENGTH, j*ONE_BLOCK_LENGTH,this);
        			if(game.getExplodeCount(i, j)<BOMB_LENGTH) {
            			game.setExplodeCount(i, j, game.getExplodeCount(i, j)+1);
            			}
        			else {
        				game.setExplodeCount(i, j, 0);
        				game.getMapBlock(i, j).setExploded(false);
        			}
        		}
        	}
        }
        gc.setClip(game.player.getX(), game.player.getY(), 36, 24);
        gc.drawImage(game.player.getImg(), game.player.getX(), game.player.getY(), this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                keyboard.update();
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
