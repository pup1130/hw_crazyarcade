package crazyarcade.graphic.mapBlock;

import java.awt.*;

public class Block {

    private boolean disappearable;
    private boolean appear = true;
    private boolean isWool = false;
    private boolean isExploded = false;
    private Image blockImage;
    private int blockNumber;
    private int woolCount = 0;
    private int explodeCount = 0;

    public Block(int blockNumber) {

        this.blockNumber = blockNumber;

        switch (blockNumber) {
            case 0: //
                appear = false;
                break;
            case 1: //
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\1.png");
                disappearable = false;
                break;
            case 2: //
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\2.png");
                disappearable = true;
                break;
            case 3: //
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\3.png");
                disappearable = false;
                break;
            case 4: //
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\4.png");
//                disappearable = false;
                break;
            case 5: //
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\5.png");
//                disappearable = false;
                break;
            case 6: //�ϴ�
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\6.png");
//                disappearable = false;
                break;
            case 256:
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\256.png");
                break;
            case 257:
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\257.png");
                break;
            default:
                break;
        }
    }

    public Image getBlockImage() {
        return blockImage;
    }

    public boolean isDisappearable() {
        return disappearable;
    }

    public boolean isAppear() {
        return appear;
    }

    public void setAppear(boolean appear) {
        this.appear = appear;
    }

    public boolean isWool() {
        return isWool;
    }

    public void setWool(boolean isWool) {
        this.isWool = isWool;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
        switch (blockNumber) {
            case 0: //
                appear = false;
                break;
            case 1: //
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\1.png");
                disappearable = false;
                break;
            case 2: //
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\2.png");
                disappearable = true;
                break;
            case 3: //
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\3.png");
                disappearable = false;
                break;
            case 4: //
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\4.png");
//                disappearable = false;
                break;
            case 5: //
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\5.png");
//                disappearable = false;
                break;
            case 6: //�ϴ�
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\6.png");
//                disappearable = false;
                break;
            case 256:
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\256.png");
                break;
            case 257:
                blockImage = Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\graphic\\mapBlock\\257.png");
                break;
            default:
                break;
        }
    }

    public boolean isExploded() {
        return isExploded;
    }

    public void setExploded(boolean isExploded) {
        this.isExploded = isExploded;
    }

    public int getWoolCount() {
        return woolCount;
    }

    public void setWoolCount(int woolCount) {
        this.woolCount = woolCount;
    }

    public int getExplodeCount() {
        return explodeCount;
    }

    public void setExplodeCount(int explodeCount) {
        this.explodeCount = explodeCount;
    }
}
