package crazyarcade;

import crazyarcade.character.Player;
import crazyarcade.exception.CAException;
import crazyarcade.graphic.mapBlock.Block;
import crazyarcade.gui.ArcadeGamePanel;
import crazyarcade.gui.Frame;

import java.awt.*;


public class Game implements Constant {

    private Frame frame;
    public Player player;
    private Keyboard keyboard;

    public static int WOOL_MAX = 2;
    public static int WOOL_HEAD = 0;
    public static int WOOL_TAIL = 0;//queue
    public static int BOMB_HEAD = 0;
    public static int BOMB_TAIL = 0;//bomb queue
    // TODO: 2018-12-05 Have to move Constant.java

    private boolean playerMove = false;


    private int[][] mapBlockNum;
    private Block[][] mapBlock = new Block[15][15];

    public Game(Frame frame, Keyboard keyboard) throws CAException {
        this.frame = frame;
        this.keyboard = keyboard;
    }

    public void init() {
        player = new Player();
        player.setX(0);
        player.setY(0);
        player.setLive(true);
        player.setItem_donut_count(0);
        player.setItem_pizza_count(0);
        player.setItem_wool_count(0);
        player.setSpeed(1.0);
        player.setLength(1);
        player.setWool_max(1);
        player.setCostume1(null);
        updateMap();
        setBlock();
    }


    private void updateMap() {
        mapBlockNum = ArcadeGamePanel.mapBlockNum;
    }

    private void setBlock() {
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

        if (keyboard.isKeyUp()) {
            if (player.getY() <= 0) {
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
                player.setY(player.getY() - player.getSpeed());
            } else {
                player.setY(player.getY() + 4);
            }
        }

        if (keyboard.isKeyDown()) {
            if (player.getY() >= MAP_SIZE - CAT_HEIGHT) {
                checkDown = false;
            }
            for (int i = 0; i < BLOCK_COUNT; i++) {
                for (int j = 0; j < BLOCK_COUNT; j++) {
                    if (mapBlock[i][j].isAppear() && !mapBlock[i][j].isWool()) {
                        if (player.getY() >= (j - 1) * ONE_BLOCK_LENGTH + ONE_BLOCK_LENGTH - CAT_HEIGHT + Y_RELAX &&
                                player.getY() <= j * ONE_BLOCK_LENGTH + ONE_BLOCK_LENGTH - CAT_HEIGHT - Y_RELAX &&
                                player.getX() >= (i - 1) * ONE_BLOCK_LENGTH + X_RELAX &&
                                player.getX() <= (i + 1) * ONE_BLOCK_LENGTH - X_RELAX) {
                            checkDown = false;
                        }
                    }
                }
            }
            if (checkDown) {
                playerMove = true;
                player.setY(player.getY() + player.getSpeed());
            } else {
                player.setY(player.getY() - 4);
            }
        }

        if (keyboard.isKeyLeft()) {
            if (player.getX() <= 0) {
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
                player.setX(player.getX() + 4);
            }
        }

        if (keyboard.isKeyRight()) {
            if (player.getX() >= MAP_SIZE - ONE_BLOCK_LENGTH) {
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
                player.setX(player.getX() - 4);
            }
        }
        if (keyboard.isKeySpace()) {

            if ((player.getX() % ONE_BLOCK_LENGTH >= WOOL_RELAX && player.getX() % ONE_BLOCK_LENGTH <= ONE_BLOCK_LENGTH - WOOL_RELAX) ||
                    (player.getY() % ONE_BLOCK_LENGTH >= WOOL_RELAX + ONE_BLOCK_LENGTH - CAT_HEIGHT && player.getY() % ONE_BLOCK_LENGTH <= ONE_BLOCK_LENGTH - WOOL_RELAX)) {
                checkSpace = false;
            }
            if (player.getWool_cur() < player.getWool_max() && checkSpace && !mapBlock[(int) (player.getX() / ONE_BLOCK_LENGTH)][(int) (player.getY() / ONE_BLOCK_LENGTH)].isWool() && WOOL_TAIL - WOOL_HEAD < WOOL_MAX) {
                if (player.getX() % ONE_BLOCK_LENGTH <= WOOL_RELAX && player.getY() % ONE_BLOCK_LENGTH <= WOOL_RELAX + ONE_BLOCK_LENGTH - CAT_HEIGHT) {
                    mapBlock[(int) (player.getX() / ONE_BLOCK_LENGTH)][(int) (player.getY() / ONE_BLOCK_LENGTH)].setBlockNumber(256);
                    mapBlock[(int) (player.getX() / ONE_BLOCK_LENGTH)][(int) (player.getY() / ONE_BLOCK_LENGTH)].setWool(true);

                } else if (player.getX() % ONE_BLOCK_LENGTH >= ONE_BLOCK_LENGTH - WOOL_RELAX && player.getY() % ONE_BLOCK_LENGTH <= WOOL_RELAX + ONE_BLOCK_LENGTH - CAT_HEIGHT) {
                    mapBlock[(int) (player.getX() / ONE_BLOCK_LENGTH + 1)][(int) (player.getY() / ONE_BLOCK_LENGTH)].setBlockNumber(256);
                    mapBlock[(int) (player.getX() / ONE_BLOCK_LENGTH + 1)][(int) (player.getY() / ONE_BLOCK_LENGTH)].setWool(true);

                } else if (player.getX() % ONE_BLOCK_LENGTH <= WOOL_RELAX && player.getY() % ONE_BLOCK_LENGTH >= ONE_BLOCK_LENGTH - WOOL_RELAX) {
                    mapBlock[(int) (player.getX() / ONE_BLOCK_LENGTH)][(int) (player.getY() / ONE_BLOCK_LENGTH + 1)].setBlockNumber(256);
                    mapBlock[(int) (player.getX() / ONE_BLOCK_LENGTH)][(int) (player.getY() / ONE_BLOCK_LENGTH + 1)].setWool(true);

                } else if (player.getX() % ONE_BLOCK_LENGTH >= ONE_BLOCK_LENGTH - WOOL_RELAX && player.getY() % ONE_BLOCK_LENGTH >= ONE_BLOCK_LENGTH - WOOL_RELAX) {
                    mapBlock[(int) (player.getX() / ONE_BLOCK_LENGTH + 1)][(int) (player.getY() / ONE_BLOCK_LENGTH + 1)].setBlockNumber(256);
                    mapBlock[(int) (player.getX() / ONE_BLOCK_LENGTH + 1)][(int) (player.getY() / ONE_BLOCK_LENGTH + 1)].setWool(true);

                }

            }
        }
        if (keyboard.isKeyC()) {
            player.setCostume1(Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\character\\costume2.png"));
        }
        if (keyboard.isKeyX()) {
            player.setCostume1(null);
        }

    }

    public Block getMapBlock(int a, int b) {
        return mapBlock[a][b];
    }

    public void setMapBlock(int a, int b, int num) {
        this.mapBlock[a][b].setBlockNumber(num);
    }

    public int getWoolCount(int a, int b) {
        return mapBlock[a][b].getWoolCount();
    }

    public void setWoolCount(int a, int b, int t) {
        mapBlock[a][b].setWoolCount(t);
    }

    public int getExplodeCount(int a, int b) {
        return mapBlock[a][b].getExplodeCount();
    }

    public void setExplodeCount(int a, int b, int t) {
        mapBlock[a][b].setExplodeCount(t);
    }

}
