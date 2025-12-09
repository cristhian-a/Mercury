package com.next.io;

import com.next.game.Game;
import com.next.system.KeyboardBind;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyboardBind.MOVE_UP) upPressed = true;
        if (code == KeyboardBind.MOVE_DOWN) downPressed = true;
        if (code == KeyboardBind.MOVE_LEFT) leftPressed = true;
        if (code == KeyboardBind.MOVE_RIGHT) rightPressed = true;
        if (code == KeyboardBind.DEBUG_MODE_1) Game.DEBUG_MODE_1 = !Game.DEBUG_MODE_1;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyboardBind.MOVE_UP) upPressed = false;
        if (code == KeyboardBind.MOVE_DOWN) downPressed = false;
        if (code == KeyboardBind.MOVE_LEFT) leftPressed = false;
        if (code == KeyboardBind.MOVE_RIGHT) rightPressed = false;
    }
}
