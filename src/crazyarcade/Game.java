package crazyarcade;

import java.awt.Color;

import crazyarcade.character.Player;
import crazyarcade.graphic.mapBlock.Block;
import crazyarcade.gui.ArcadeGamePanel;
import crazyarcade.gui.Frame;

public class Game extends Frame implements Runnable {

    private static final int BLOCK_COUNT = 15;
    private static final int ONE_BLOCK_LENGTH = 36;
    private static final int Y_RELAX = 2;
    private static final int X_RELAX = 2;
    private static final int CAT_HEIGHT = 24;
    private static final int MAP_SIZE = 540;
    private static final int WOOL_RELAX = 2;

    public static int WOOL_MAX = 2;
    public static int WOOL_HEAD = 0;
    public static int WOOL_TAIL = 0;//queue
    public static int BOMB_HEAD = 0;
    public static int BOMB_TAIL = 0;//bomb queue

    boolean keyUp = false;
    boolean keyDown = false;
    boolean keyLeft = false;
    boolean keyRight = false;
    boolean keySpace = false;
    boolean playerMove = false;


    private Keyboard input;
    private Player player;

    int[][] mapBlockNum;
    Block[][] mapBlock = new Block[15][15];

    private int x, y;
    private int cnt;
    Thread thread;

    private void init() {
        player.setX(0);
        player.setY(0);
    }

    public void start() {
        input = new Keyboard();
        this.addKeyListener(input);
        thread = new Thread(this);
        try {
            thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setBackground(Color.RED);
    }

    @Override
    public void run() {
        while (true) {
            try {
                keyProcess();
                repaint();

                Thread.sleep(20);
                cnt++;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateMap() {
        mapBlockNum = ArcadeGamePanel.mapBlock;
    }

    public void setBlock() {
        for (int i = 0; i < BLOCK_COUNT; i++) {
            for (int j = 0; j < BLOCK_COUNT; j++) {
                mapBlock[i][j] = new Block(mapBlockNum[i][j]);
            }
        }
    }

    public void keyProcess() {
        playerMove = false;
        boolean checkUp = true;
        boolean checkDown = true;
        boolean checkLeft = true;
        boolean checkRight = true;
        boolean checkSpace = true;

        if (input.isKeyUp()) {
            if (player.getY() <= 2) {
                checkUp = false;
            }
            for (int i = 0; i < BLOCK_COUNT; i++) {
                for (int j = 0; j < BLOCK_COUNT; j++) {
                    if (mapBlock[i][j].isAppear() && !mapBlock[i][j].isWool()) {
                        if (player.getY() <= (j + 1) * ONE_BLOCK_LENGTH - Y_RELAX &&
                                player.getY() >= j * ONE_BLOCK_LENGTH + Y_RELAX &&
                                player.getX() >= (i - 1) * ONE_BLOCK_LENGTH + X_RELAX &&
                                player.getX() <= (i + 1) * ONE_BLOCK_LENGTH - X_RELAX) {
                            checkUp = false;
                        }
                    }
                }
            }
            if (checkUp) {
                playerMove = true;
                player.setY(getY() - player.getSpeed());
            } else {
                //				y+=4;
            }
        }

        if (input.isKeyDown()) {
            if (player.getY() >= MAP_SIZE + 2 - CAT_HEIGHT) {
                checkDown = false;
            }
            for (int i = 0; i < BLOCK_COUNT; i++) {
                for (int j = 0; j < BLOCK_COUNT; j++) {
                    if (mapBlock[i][j].isAppear() && !mapBlock[i][j].isWool()) {
                        if (player.getY() >= (j - 1) * ONE_BLOCK_LENGTH + ONE_BLOCK_LENGTH - CAT_HEIGHT + Y_RELAX &&
                                player.getY() <= j * ONE_BLOCK_LENGTH + ONE_BLOCK_LENGTH - CAT_HEIGHT - Y_RELAX &&
                                player.getX() >= (i - 1) * ONE_BLOCK_LENGTH + 2 + X_RELAX &&
                                player.getX() <= (i + 1) * ONE_BLOCK_LENGTH + 2 - X_RELAX) {
                            checkDown = false;
                        }
                    }
                }
            }
            if (checkDown) {
                player.setY(player.getY() + player.getSpeed());
                playerMove = true;
            } else {
                //				y-=4;
            }
        }

        if (input.isKeyLeft()) {
            if (player.getX() <= 2) {
                checkLeft = false;
            }
            for (int i = 0; i < BLOCK_COUNT; i++) {
                for (int j = 0; j < BLOCK_COUNT; j++) {
                    if (mapBlock[i][j].isAppear() && !mapBlock[i][j].isWool()) {
                        if (player.getX() <= (i + 1) * ONE_BLOCK_LENGTH + 2 - X_RELAX &&
                                player.getX() >= i * ONE_BLOCK_LENGTH + 2 + X_RELAX &&
                                player.getY() >= j * ONE_BLOCK_LENGTH - CAT_HEIGHT + Y_RELAX &&
                                player.getY() <= (j + 1) * ONE_BLOCK_LENGTH - Y_RELAX) {
                            checkLeft = false;
                        }
                    }
                }
            }
            if (checkLeft) {
                player.setX(player.getX() - player.getSpeed());
                playerMove = true;
            } else {
                //				x+=4;
            }
        }

        if (input.isKeyRight()) {
            if (player.getX() >= 2 + MAP_SIZE - ONE_BLOCK_LENGTH) {
                checkRight = false;
            }
            for (int i = 0; i < BLOCK_COUNT; i++) {
                for (int j = 0; j < BLOCK_COUNT; j++) {
                    if (mapBlock[i][j].isAppear() && !mapBlock[i][j].isWool()) {
                        if (player.getX() >= (i - 1) * ONE_BLOCK_LENGTH + 2 + X_RELAX &&
                                player.getX() <= i * ONE_BLOCK_LENGTH + 2 - X_RELAX &&
                                player.getY() >= j * ONE_BLOCK_LENGTH - CAT_HEIGHT + Y_RELAX &&
                                player.getY() <= (j + 1) * ONE_BLOCK_LENGTH - Y_RELAX) {
                            checkRight = false;
                        }
                    }
                }
            }
            if (checkRight) {
                player.setX(player.getX() + player.getSpeed());
                playerMove = true;
            } else {
                //				x-=4;
            }
        }
        if (input.isKeySpace()) {

            if ((player.getX() % ONE_BLOCK_LENGTH >= WOOL_RELAX && player.getX() % ONE_BLOCK_LENGTH <= ONE_BLOCK_LENGTH - WOOL_RELAX) ||
                    (player.getY() % ONE_BLOCK_LENGTH >= WOOL_RELAX + ONE_BLOCK_LENGTH - CAT_HEIGHT && player.getY() % ONE_BLOCK_LENGTH <= ONE_BLOCK_LENGTH - WOOL_RELAX)) {
                checkSpace = false;
            }
            if (checkSpace && !mapBlock[player.getX() / ONE_BLOCK_LENGTH][player.getY() / ONE_BLOCK_LENGTH].isWool() && WOOL_TAIL - WOOL_HEAD < WOOL_MAX) {
                if (player.getX() % ONE_BLOCK_LENGTH <= WOOL_RELAX && player.getY() % ONE_BLOCK_LENGTH <= WOOL_RELAX + ONE_BLOCK_LENGTH - CAT_HEIGHT) {
                    mapBlock[player.getX() / ONE_BLOCK_LENGTH][player.getY() / ONE_BLOCK_LENGTH].setBlockNumber(256);
                } else if (player.getX() % ONE_BLOCK_LENGTH >= ONE_BLOCK_LENGTH - WOOL_RELAX && player.getY() % ONE_BLOCK_LENGTH <= WOOL_RELAX + ONE_BLOCK_LENGTH - CAT_HEIGHT) {
                    mapBlock[player.getX() / ONE_BLOCK_LENGTH + 1][player.getY() / ONE_BLOCK_LENGTH].setBlockNumber(256);
                } else if (player.getX() % ONE_BLOCK_LENGTH <= WOOL_RELAX && player.getY() % ONE_BLOCK_LENGTH >= ONE_BLOCK_LENGTH - WOOL_RELAX) {
                    mapBlock[player.getX() / ONE_BLOCK_LENGTH][player.getY() / ONE_BLOCK_LENGTH + 1].setBlockNumber(256);
                } else if (player.getX() % ONE_BLOCK_LENGTH >= ONE_BLOCK_LENGTH - WOOL_RELAX && player.getY() % ONE_BLOCK_LENGTH >= ONE_BLOCK_LENGTH - WOOL_RELAX) {
                    mapBlock[player.getX() / ONE_BLOCK_LENGTH + 1][player.getY() / ONE_BLOCK_LENGTH + 1].setBlockNumber(256);
                }
                mapBlock[player.getX() / ONE_BLOCK_LENGTH][player.getY() / ONE_BLOCK_LENGTH].setWool(true);
            }
        }

    }


}
