import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
    This code isn't in use at this time.
 */


public class CA extends JFrame implements Runnable, KeyListener {

    private static final long serialVersionUID = -918735028859458194L;

    static final int ONE_BLOCK_LENGTH = 85;
    private static final int MAP_SIZE = 1020;
    private static final int CAT_HEIGHT = 60;
    private static final int Y_RELAX = 5;
    private static final int X_RELAX = 10;
    private static final int WOOL_RELAX = 10;//털실을 놓을 때 인식되는 범위
    private static final int speed = 2;
    private static final int BLAST_TIME = 150;//털실 터지는 시간
    private static final int BOMB_LENGTH = 3;
    private static final int BOMB_TIME_LENGTH = 50;//털실 터지는 동안의 시간

    private static int BLOCK_COUNT = 106;
    private static int WOOL_MAX = 2;
    private static int WOOL_HEAD = 0;
    private static int WOOL_TAIL = 0;//queue 구현
    private static int BOMB_HEAD = 0;
    private static int BOMB_TAIL = 0;//bomb 에 대한 queue 구현

    private boolean keyUp = false;
    private boolean keyDown = false;
    private boolean keyLeft = false;
    private boolean keyRight = false;
    private boolean keySpace = false;
    private boolean playerMove = false;

    private int tm = 0;
    private Block[] block = new Block[144];
    private Block[] wools = new Block[200];
    private int[][] IsBlock = new int[12][12];//블럭이 없는 경우는 -1, 있는 경우 블럭의 번호
    private int[] flag1 = new int[200];//wool 하나에 한층만 터지도록,j값을 저장,-1로 초기화
    private int[] flag2 = new int[200];//wool 하나에 한층만 터지도록
    private int[] flag3 = new int[200];//wool 하나에 한층만 터지도록
    private int[] flag4 = new int[200];//wool 하나에 한층만 터지도록
    private boolean[][] IsWool = new boolean[12][12];
    private Toolkit tk = Toolkit.getDefaultToolkit();

    private Image img = new ImageIcon("C:\\Users\\HoJun\\Pictures\\ket\\ket.png").getImage();
    private Image wool = new ImageIcon("C:\\Users\\HoJun\\Pictures\\ket\\털실.png").getImage();
    private Image bomb = new ImageIcon("C:\\Users\\HoJun\\Pictures\\ket\\bomb.png").getImage();

    private Image buffimg;
    static Graphics gc;
    private Thread thread;

    private int x, y;
    private int cnt;
    private int moveStatus;


    private void setIsBlock() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                IsBlock[i][j] = -1;
            }
        }
        for (int i = 0; i < BLOCK_COUNT; i++) {
            IsBlock[block[i].nx][block[i].ny] = i;
        }
    }

    private void setflags() {
        for (int i = 0; i < 200; i++) {
            flag1[i] = -1;
            flag2[i] = -1;
            flag3[i] = -1;
            flag4[i] = -1;
        }
    }

    CA() {
        setTitle("Crazy Arcade");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        init();
        start();
        setBackground();
        setIsBlock();
        setflags();
        Dimension screen = tk.getScreenSize();

        int xpos = (int) (screen.getWidth() / 2 - getWidth() / 2);
        int ypos = (int) (screen.getHeight() / 2 - getHeight() / 2);
        setLocation(xpos, ypos);
        setResizable(false);
        setVisible(true);
    }

    private void setBackground() {

        block[0] = new Block(1, 3, Color.PINK, false);
        block[1] = new Block(2, 3, Color.PINK, false);
        block[2] = new Block(1, 4, Color.PINK, false);
        block[3] = new Block(2, 4, Color.PINK, false);
        block[4] = new Block(4, 2, Color.PINK, false);
        block[5] = new Block(4, 3, Color.PINK, false);
        block[6] = new Block(5, 2, Color.PINK, false);
        block[7] = new Block(5, 3, Color.PINK, false);
        block[8] = new Block(7, 2, Color.PINK, false);
        block[9] = new Block(7, 3, Color.PINK, false);
        block[10] = new Block(8, 2, Color.PINK, false);
        block[11] = new Block(8, 3, Color.PINK, false);
        block[12] = new Block(9, 5, Color.PINK, false);
        block[13] = new Block(9, 6, Color.PINK, false);
        block[14] = new Block(10, 5, Color.PINK, false);
        block[15] = new Block(10, 6, Color.PINK, false);
        block[16] = new Block(1, 7, Color.PINK, false);
        block[17] = new Block(2, 7, Color.PINK, false);
        block[18] = new Block(2, 8, Color.PINK, false);
        block[19] = new Block(3, 6, Color.PINK, false);
        block[20] = new Block(3, 7, Color.PINK, false);
        block[21] = new Block(3, 8, Color.PINK, false);
        block[22] = new Block(4, 5, Color.PINK, false);
        block[23] = new Block(4, 6, Color.PINK, false);
        block[24] = new Block(4, 7, Color.PINK, false);
        block[25] = new Block(4, 8, Color.PINK, false);
        block[26] = new Block(5, 5, Color.PINK, false);
        block[27] = new Block(5, 6, Color.PINK, false);
        block[28] = new Block(5, 7, Color.PINK, false);
        block[29] = new Block(5, 8, Color.PINK, false);
        block[30] = new Block(5, 9, Color.PINK, false);
        block[31] = new Block(6, 5, Color.PINK, false);
        block[32] = new Block(6, 6, Color.PINK, false);
        block[33] = new Block(6, 7, Color.PINK, false);
        block[34] = new Block(6, 8, Color.PINK, false);
        block[35] = new Block(6, 9, Color.PINK, false);
        block[36] = new Block(7, 6, Color.PINK, false);
        block[37] = new Block(7, 7, Color.PINK, false);
        block[38] = new Block(7, 8, Color.PINK, false);
        block[39] = new Block(7, 9, Color.PINK, false);
        block[40] = new Block(8, 8, Color.PINK, false);
        block[41] = new Block(8, 9, Color.PINK, false);
        block[42] = new Block(0, 2, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[43] = new Block(0, 3, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[44] = new Block(0, 4, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[45] = new Block(0, 7, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[46] = new Block(0, 8, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[47] = new Block(1, 1, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[48] = new Block(1, 2, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[49] = new Block(1, 5, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[50] = new Block(1, 6, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[51] = new Block(1, 8, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[52] = new Block(1, 9, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[53] = new Block(2, 1, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[54] = new Block(2, 2, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[55] = new Block(2, 5, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[56] = new Block(2, 6, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[57] = new Block(2, 9, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[58] = new Block(3, 0, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[59] = new Block(3, 1, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[60] = new Block(3, 2, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[61] = new Block(3, 3, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[62] = new Block(3, 5, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[63] = new Block(3, 9, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[64] = new Block(3, 10, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[65] = new Block(4, 0, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[66] = new Block(4, 1, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[67] = new Block(4, 4, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[68] = new Block(4, 9, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[69] = new Block(4, 10, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[70] = new Block(4, 11, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[71] = new Block(5, 1, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[72] = new Block(5, 4, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[73] = new Block(5, 10, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[74] = new Block(5, 11, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[75] = new Block(6, 1, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[76] = new Block(6, 2, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[77] = new Block(6, 4, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[78] = new Block(6, 10, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[79] = new Block(6, 11, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[80] = new Block(7, 1, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[81] = new Block(7, 4, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[82] = new Block(7, 5, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[83] = new Block(7, 10, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[84] = new Block(8, 1, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[85] = new Block(8, 4, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[86] = new Block(8, 6, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[87] = new Block(8, 7, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[88] = new Block(8, 10, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[89] = new Block(9, 0, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[90] = new Block(9, 1, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[91] = new Block(9, 2, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[92] = new Block(9, 3, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[93] = new Block(9, 4, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[94] = new Block(9, 7, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[95] = new Block(9, 8, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[96] = new Block(9, 9, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[97] = new Block(10, 2, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[98] = new Block(10, 3, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[99] = new Block(10, 7, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[100] = new Block(10, 9, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[101] = new Block(10, 10, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[102] = new Block(11, 2, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[103] = new Block(11, 5, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[104] = new Block(11, 6, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);
        block[105] = new Block(11, 10, Color.getHSBColor(0.0f, 0.0f, 0.9f), true);

    }

    private void init() {
        x = 0;
        y = 0;

        moveStatus = 2;
        // 캐릭터가 시작할때 바라보는 방향은 아래쪽입니다.
        // 0 : 위쪽, 1 : 오른쪽, 2 : 아래쪽, 3 : 왼쪽
    }

    public void run() { // 스레드 메소드, 무한 루프
        while (true) {
            try {
                keyProcess();
                repaint();

                Thread.sleep(20);
                cnt++;

            } catch (Exception e) {
            }
        }
    }

    private void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g) {
        buffimg = createImage(1022, 1022);
        gc = buffimg.getGraphics();
        update(g);
    }

    public void update(Graphics g) {
        // 더블 버퍼링을 이용해 버퍼에 그려진것을 가져옵니다.
        DrawImg();

        g.drawImage(buffimg, 2, 56, this);
    }

    private void DrawImg() {
        gc.setFont(new Font("Default", Font.BOLD, 20));
        /*
         * gc.drawString(Integer.toString(cnt), 50, 50);
         * gc.drawString(Integer.toString((playerMove)?1:0),200, 50);
         */
        // 위는 단순히 무한루프 적용여부와 케릭터 방향 체크를 위해
        // 눈으로 보면서 테스트할 용도로 쓰이는 텍스트 표출입니다.

        MoveImage(img, x, y, 92, 60);
        //WoolGen(wool,);
        // 케릭터를 걸어가게 만들기 위해 추가로 만든 메소드 입니다.
    }

    private void WoolGen(int nx, int ny) {
        WOOL_TAIL++;
        wools[WOOL_TAIL - 1] = new Block(nx, ny, Color.WHITE, true);

    }

    private void MoveImage(Image img, int x, int y, int width, int height) {
        // 케릭터 이미지, 케릭터 위치, 케릭터 크기를 받습니다.
        // 받은 값을 이용해서 위의 이미지칩셋에서 케릭터를 잘라내
        // 표출하도록 계산하는 메소드 입니다.
        gc.setColor(Color.WHITE);
        gc.fillRect(0, 0, 1020, 1020);
        for (int i = 0; i < BLOCK_COUNT; i++) {
            block[i].drawBlock();
        }
        for (int i = WOOL_HEAD; i < WOOL_TAIL; i++) {
            gc.drawImage(wool, 2 + wools[i].nx * ONE_BLOCK_LENGTH, wools[i].ny * ONE_BLOCK_LENGTH, this);
        }
        for (int i = BOMB_HEAD; i < BOMB_TAIL; i++) {
            gc.drawImage(bomb, 2 + wools[i].nx * ONE_BLOCK_LENGTH, wools[i].ny * ONE_BLOCK_LENGTH, this);
            IsWool[wools[i].nx][wools[i].ny] = false;
            for (int j = 1; j <= BOMB_LENGTH; j++) {
                if (flag1[i] == -1) {
                    if (wools[i].nx + j >= 12 || IsBlock[wools[i].nx + j][wools[i].ny] >= 0) {
                        if (wools[i].nx + j < 12 && !block[IsBlock[wools[i].nx + j][wools[i].ny]].removeable) {
                            flag1[i] = j - 1;
                            break;
                        } else if (wools[i].nx + j < 12 && block[IsBlock[wools[i].nx + j][wools[i].ny]].removeable) {
                            block[IsBlock[wools[i].nx + j][wools[i].ny]].disappear();
                            IsBlock[wools[i].nx + j][wools[i].ny] = -1;
                            flag1[i] = j;
                            break;
                        }
                    }
                }
                if (j <= flag1[i] || flag1[i] == -1) {
                    gc.drawImage(bomb, 2 + (wools[i].nx + j) * ONE_BLOCK_LENGTH, wools[i].ny * ONE_BLOCK_LENGTH, this);
                }
            }
            for (int j = 1; j <= BOMB_LENGTH; j++) {
                if (flag2[i] == -1) {
                    if (wools[i].nx - j < 0 || IsBlock[wools[i].nx - j][wools[i].ny] >= 0) {
                        if (wools[i].nx - j >= 0 && !block[IsBlock[wools[i].nx - j][wools[i].ny]].removeable) {
                            flag2[i] = j - 1;
                            break;
                        } else if (wools[i].nx - j >= 0 && block[IsBlock[wools[i].nx - j][wools[i].ny]].removeable) {
                            block[IsBlock[wools[i].nx - j][wools[i].ny]].disappear();
                            IsBlock[wools[i].nx - j][wools[i].ny] = -1;
                            flag2[i] = j;
                            break;
                        }
                    }
                }
                if (j <= flag2[i] || flag2[i] == -1) {
                    gc.drawImage(bomb, 2 + (wools[i].nx - j) * ONE_BLOCK_LENGTH, wools[i].ny * ONE_BLOCK_LENGTH, this);
                }
            }
            for (int j = 1; j <= BOMB_LENGTH; j++) {
                if (flag3[i] == -1) {
                    if (wools[i].ny + j >= 12 || IsBlock[wools[i].nx][wools[i].ny + j] >= 0) {
                        if (wools[i].ny + j < 12 && !block[IsBlock[wools[i].nx][wools[i].ny + j]].removeable) {
                            flag3[i] = j - 1;
                            break;
                        } else if (wools[i].ny + j < 12 && block[IsBlock[wools[i].nx][wools[i].ny + j]].removeable) {
                            block[IsBlock[wools[i].nx][wools[i].ny + j]].disappear();
                            IsBlock[wools[i].nx][wools[i].ny + j] = -1;
                            flag3[i] = j;
                            break;
                        }
                    }
                }
                if (j <= flag3[i] || flag3[i] == -1) {
                    gc.drawImage(bomb, 2 + wools[i].nx * ONE_BLOCK_LENGTH, (wools[i].ny + j) * ONE_BLOCK_LENGTH, this);
                }
            }
            for (int j = 1; j <= BOMB_LENGTH; j++) {
                if (flag4[i] == -1) {
                    if (wools[i].ny - j < 0 || IsBlock[wools[i].nx][wools[i].ny - j] >= 0) {
                        if (wools[i].ny - j >= 0 && !block[IsBlock[wools[i].nx][wools[i].ny - j]].removeable) {
                            flag4[i] = j - 1;
                            break;
                        } else if (wools[i].ny - j >= 0 && block[IsBlock[wools[i].nx][wools[i].ny - j]].removeable) {
                            block[IsBlock[wools[i].nx][wools[i].ny - j]].disappear();
                            IsBlock[wools[i].nx][wools[i].ny - j] = -1;
                            flag4[i] = j;
                            break;
                        }
                    }
                }
                if (j <= flag4[i] || flag4[i] == -1) {
                    gc.drawImage(bomb, 2 + wools[i].nx * ONE_BLOCK_LENGTH, (wools[i].ny - j) * ONE_BLOCK_LENGTH, this);
                }
            }
        }
        gc.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= 12; i++) {
            gc.fillRect(85 * i, 0, 2, 1020);
            gc.fillRect(0, 85 * i, 1020, 2);
        }
        gc.setClip(x, y, width, height);
        // 현재 좌표에서 케릭터의 크기 만큼 이미지를 잘라 그립니다.
        tm++;
        if (tm >= 50) {
            tm %= 50;
        }
        if (tm > 40) {
            gc.drawImage(img, x, y - 60, this);
        } else {
            gc.drawImage(img, x, y, this);
        }
        for (int i = WOOL_HEAD; i < WOOL_TAIL; i++) {
            wools[i].blast_time++;
        }
        if (WOOL_HEAD < WOOL_TAIL && wools[WOOL_HEAD].blast_time > BLAST_TIME) {
            WOOL_HEAD++;
            BOMB_TAIL++;
        }
        for (int i = BOMB_HEAD; i < BOMB_TAIL; i++) {
            wools[i].bomb_time++;
        }
        if (BOMB_HEAD < BOMB_TAIL && wools[BOMB_HEAD].bomb_time > BOMB_TIME_LENGTH) {
            BOMB_HEAD++;
        }
    }

    private void keyProcess() {
        // 여기서는 단순 케릭터가 이동하는 좌표 말고도
        // 케릭터의 움직임 여부및 방향을 체크 합니다.
        playerMove = false;
        boolean checkUp = true; //check를 공유하면 오른쪽으로 막힌 채 위로 빠르게 이동가능하다.
        boolean checkDown = true;
        boolean checkLeft = true;
        boolean checkRight = true;
        boolean checkSpace = true;
        if (keyUp) {
            if (y <= 2) {
                checkUp = false;
            }
            for (int i = 0; i < BLOCK_COUNT; i++) {
                if (block[i].appear && !block[i].isWool) {
                    if (y <= (block[i].ny + 1) * ONE_BLOCK_LENGTH - Y_RELAX &&
                            y >= (block[i].ny) * ONE_BLOCK_LENGTH + Y_RELAX &&
                            x >= (block[i].nx - 1) * ONE_BLOCK_LENGTH + 2 + X_RELAX &&
                            x <= (block[i].nx + 1) * ONE_BLOCK_LENGTH + 2 - X_RELAX) {
                        checkUp = false;
                    }
                }
            }
            if (checkUp) {
                playerMove = true;
                y -= speed;
                moveStatus = 0;
            } else {
                y += 4;
            }
        }

        if (keyDown) {
            if (y >= MAP_SIZE + 2 - CAT_HEIGHT) {
                checkDown = false;
            }
            for (int i = 0; i < BLOCK_COUNT; i++) {
                if (block[i].appear && !block[i].isWool) {
                    if (y >= (block[i].ny - 1) * ONE_BLOCK_LENGTH + ONE_BLOCK_LENGTH - CAT_HEIGHT + Y_RELAX &&
                            y <= (block[i].ny) * ONE_BLOCK_LENGTH + ONE_BLOCK_LENGTH - CAT_HEIGHT - Y_RELAX &&
                            x >= (block[i].nx - 1) * ONE_BLOCK_LENGTH + 2 + X_RELAX &&
                            x <= (block[i].nx + 1) * ONE_BLOCK_LENGTH + 2 - X_RELAX) {
                        checkDown = false;
                    }
                }
            }
            if (checkDown) {
                y += speed;
                moveStatus = 2;
                playerMove = true;
            } else {
                y -= 4;
            }
        }

        if (keyLeft) {
            if (x <= 2) {
                checkLeft = false;
            }
            for (int i = 0; i < BLOCK_COUNT; i++) {
                if (block[i].appear && !block[i].isWool) {
                    if (x <= (block[i].nx + 1) * ONE_BLOCK_LENGTH + 2 - X_RELAX &&
                            x >= (block[i].nx) * ONE_BLOCK_LENGTH + 2 + X_RELAX &&
                            y >= block[i].ny * ONE_BLOCK_LENGTH - CAT_HEIGHT + Y_RELAX &&
                            y <= (block[i].ny + 1) * ONE_BLOCK_LENGTH - Y_RELAX) {
                        checkLeft = false;
                    }
                }
            }
            if (checkLeft) {
                x -= speed;
                moveStatus = 3;
                playerMove = true;
            } else {
                x += 4;
            }
        }

        if (keyRight) {
            if (x >= 2 + MAP_SIZE - ONE_BLOCK_LENGTH) {
                checkRight = false;
            }
            for (int i = 0; i < BLOCK_COUNT; i++) {
                if (block[i].appear && !block[i].isWool) {
                    if (x >= (block[i].nx - 1) * ONE_BLOCK_LENGTH + 2 + X_RELAX &&
                            x <= (block[i].nx) * ONE_BLOCK_LENGTH + 2 - X_RELAX &&
                            y >= block[i].ny * ONE_BLOCK_LENGTH - CAT_HEIGHT + Y_RELAX &&
                            y <= (block[i].ny + 1) * ONE_BLOCK_LENGTH - Y_RELAX) {
                        checkRight = false;
                    }
                }
            }
            if (checkRight) {
                x += speed;
                moveStatus = 1;
                playerMove = true;
            } else {
                x -= 4;
            }
        }
        if (keySpace) {

            if ((x % ONE_BLOCK_LENGTH >= WOOL_RELAX && x % ONE_BLOCK_LENGTH <= ONE_BLOCK_LENGTH - WOOL_RELAX) ||
                    (y % ONE_BLOCK_LENGTH >= WOOL_RELAX + ONE_BLOCK_LENGTH - CAT_HEIGHT && y % ONE_BLOCK_LENGTH <= ONE_BLOCK_LENGTH - WOOL_RELAX)) {
                checkSpace = false;
            }
            if (checkSpace && !IsWool[x / ONE_BLOCK_LENGTH][y / ONE_BLOCK_LENGTH] && WOOL_TAIL - WOOL_HEAD < WOOL_MAX) {
                if (x % ONE_BLOCK_LENGTH <= WOOL_RELAX && y % ONE_BLOCK_LENGTH <= WOOL_RELAX + ONE_BLOCK_LENGTH - CAT_HEIGHT) {
                    WoolGen(x / ONE_BLOCK_LENGTH, y / ONE_BLOCK_LENGTH);
                } else if (x % ONE_BLOCK_LENGTH >= ONE_BLOCK_LENGTH - WOOL_RELAX && y % ONE_BLOCK_LENGTH <= WOOL_RELAX + ONE_BLOCK_LENGTH - CAT_HEIGHT) {
                    WoolGen(x / ONE_BLOCK_LENGTH + 1, y / ONE_BLOCK_LENGTH);
                } else if (x % ONE_BLOCK_LENGTH <= WOOL_RELAX && y % ONE_BLOCK_LENGTH >= ONE_BLOCK_LENGTH - WOOL_RELAX) {
                    WoolGen(x / ONE_BLOCK_LENGTH, y / ONE_BLOCK_LENGTH + 1);
                } else if (x % ONE_BLOCK_LENGTH >= ONE_BLOCK_LENGTH - WOOL_RELAX && y % ONE_BLOCK_LENGTH >= ONE_BLOCK_LENGTH - WOOL_RELAX) {
                    WoolGen(x / ONE_BLOCK_LENGTH + 1, y / ONE_BLOCK_LENGTH + 1);
                }
                IsWool[x / ONE_BLOCK_LENGTH][y / ONE_BLOCK_LENGTH] = true;
            }
        }
    }

    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                keyLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                keyRight = true;
                break;
            case KeyEvent.VK_UP:
                keyUp = true;
                break;
            case KeyEvent.VK_DOWN:
                keyDown = true;
                break;
            case KeyEvent.VK_SPACE:
                keySpace = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                keyLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                keyRight = false;
                break;
            case KeyEvent.VK_UP:
                keyUp = false;
                break;
            case KeyEvent.VK_DOWN:
                keyDown = false;
                break;
            case KeyEvent.VK_SPACE:
                keySpace = false;
                break;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

}

class Block {
    int nx, ny;// *85 nx열 ny행 블럭
    private Color myColor;// 블럭 색
    boolean removeable;
    boolean appear = true;
    boolean isWool = false;
    int blast_time = 0;
    int bomb_time = 0;//터지는 동안의 시간

    Block(int nx, int ny, Color myColor, boolean removeable) {
        this.nx = nx;
        this.ny = ny;
        this.myColor = myColor;
        this.removeable = removeable;
        if (myColor == Color.WHITE) {
            isWool = true;
        }
    }

    void drawBlock() {
        if (appear) {
            CA.gc.setColor(myColor);
            CA.gc.fillRect(2 + nx * CA.ONE_BLOCK_LENGTH, ny * CA.ONE_BLOCK_LENGTH, CA.ONE_BLOCK_LENGTH, CA.ONE_BLOCK_LENGTH);
        }
    }

    void disappear() {
        if (!removeable)
            return;
        appear = false;

    }
}
