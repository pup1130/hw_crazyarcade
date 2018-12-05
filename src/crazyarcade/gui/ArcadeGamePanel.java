package crazyarcade.gui;

import crazyarcade.Constant;
import crazyarcade.Game;
import crazyarcade.Keyboard;
import crazyarcade.character.Enemy;
import crazyarcade.character.Item;
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
		buffimg = createImage(1080, 542);
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
		gc.setColor(Color.PINK);
		gc.fillRect(542, 0, 538, 542);
		for(int i=0;i<game.player.getItem_pizza_count();i++) {
			gc.drawImage(Item.pizza, 600+36*(i%8), 72+36*(i/8), this);
		}
		for(int i=0;i<game.player.getItem_donut_count();i++) {
			gc.drawImage(Item.donut, 600+36*(i%8), 144+36*(i/8), this);
		}
		for(int i=0;i<game.player.getItem_wool_count();i++) {
			gc.drawImage(Item.wool, 600+36*(i%8), 216+36*(i/8), this);
		}
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
					gc.drawImage(game.getMapBlock(i, j).getBlockImage(), i * ONE_BLOCK_LENGTH, j * ONE_BLOCK_LENGTH,this);
					if(game.getWoolCount(i, j)<WOOL_LENGTH) {
						if(game.getWoolCount(i, j)==0) {
							game.player.setWool_cur(game.player.getWool_cur()+1);
						}
						game.setWoolCount(i, j, game.getWoolCount(i, j)+1);
					}
					else {
						game.setWoolCount(i, j, 0);
						game.player.setWool_cur(game.player.getWool_cur()-1);
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
										if(game.getMapBlock(i+k*dir[di][0],j+k*dir[di][1]).isDisappearable()) {
											game.getMapBlock(i+k*dir[di][0],j+k*dir[di][1]).setExploded(true);
											game.setMapBlock(i+k*dir[di][0],j+k*dir[di][1],257);
											int a=Item.giveItem();
											if(a==1) {
												game.player.setItem_pizza_count(game.player.getItem_pizza_count()+1);
												game.player.setSpeed(game.player.getSpeed()+0.2);
											}
											if(a==2) {
												game.player.setItem_donut_count(game.player.getItem_donut_count()+1);
												game.player.setLength(game.player.getLength()+1);
											}
											if(a==3) {
												game.player.setItem_wool_count(game.player.getItem_wool_count()+1);
												game.player.setWool_max(game.player.getWool_max()+1);
											}
										}
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
						if(game.player.getX()>=(i-1)*ONE_BLOCK_LENGTH+X_RELAX&&game.player.getX()<=(i+1)*ONE_BLOCK_LENGTH-X_RELAX
								&&game.player.getY()>=j*ONE_BLOCK_LENGTH-CAT_HEIGHT+Y_RELAX&&game.player.getY()<=(j+1)*ONE_BLOCK_LENGTH-Y_RELAX) {
							game.player.setLive(false);
						}
					}
					else {
						game.setExplodeCount(i, j, 0);
						game.getMapBlock(i, j).setExploded(false);
						game.setMapBlock(i, j, 0);
					}
				}
			}
		}
		gc.drawImage(Enemy.img,36*6,36*5,this);
		//gc.setClip((int)game.player.getX(), (int)game.player.getY(), 36, 24);
		gc.drawImage(game.player.getImg(), (int)game.player.getX(), (int)game.player.getY(), this);
		if(game.player.getCostume1()!=null) {
			gc.drawImage(game.player.getCostume1(), (int)game.player.getX(), (int)game.player.getY(), this);
		}
		
		
	}

	@Override
	public void run() {
		while (true) {
			try {
				//            	if(!game.player.isLive()) {
				//            		thread.stop();
				//            	}
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
