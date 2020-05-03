package GUI;

import gameLogic.GameLogic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class MyKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        GameLogic logic = MyPanel.getInstance().getLogic();
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            logic.addRequest(GameLogic.RequestType.MOVE_RIGHT);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            logic.addRequest(GameLogic.RequestType.MOVE_LEFT);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            logic.addRequest(GameLogic.RequestType.MOVE_UP);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            logic.addRequest(GameLogic.RequestType.MOVE_DOWN);
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
