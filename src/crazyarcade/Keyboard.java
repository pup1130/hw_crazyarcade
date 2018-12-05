package crazyarcade;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private boolean[] keys = new boolean[120];
    private boolean keyUp;
    private boolean keyDown;
    private boolean keyLeft;
    private boolean keyRight;
    private boolean keySpace;
    private boolean keyC;
    private boolean keyX;

    public void update() {
        keyUp = keys[KeyEvent.VK_UP];
        keyDown = keys[KeyEvent.VK_DOWN];
        keyLeft = keys[KeyEvent.VK_LEFT];
        keyRight = keys[KeyEvent.VK_RIGHT];
        keySpace = keys[KeyEvent.VK_SPACE];
        keyC = keys[KeyEvent.VK_C];
        keyX = keys[KeyEvent.VK_X];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }


    public boolean isKeyUp() {
        return keyUp;
    }

    public boolean isKeyDown() {
        return keyDown;
    }

    public boolean isKeyLeft() {
        return keyLeft;
    }

    public boolean isKeyRight() {
        return keyRight;
    }

    public boolean isKeySpace() {
        return keySpace;
    }

    public boolean isKeyC() {
        return keyC;
    }

    public boolean isKeyX() {
        return keyX;
    }
}
