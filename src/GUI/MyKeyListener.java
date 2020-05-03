package GUI;

import gameLogic.Board;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class MyKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        Board logic = MyPanel.getInstance().getLogic();
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            logic.addRequest(Board.RequestType.MOVE_RIGHT);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            logic.addRequest(Board.RequestType.MOVE_LEFT);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            logic.addRequest(Board.RequestType.MOVE_UP);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            logic.addRequest(Board.RequestType.MOVE_DOWN);
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
