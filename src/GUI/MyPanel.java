package GUI;

import gameLogic.Board;
import models.PuzzlePiece;
import utils.Constants;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    private static MyPanel panelInstance;
    private Board logic;


    private MyPanel() {
        init();
    }

    private void init() {
        setSize();
        logic = new Board();
    }

    private void setSize() {
        int screenWidth, screenHeight;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int maxSize = Math.max(screenWidth, screenHeight) / 3;
        this.setSize(maxSize, maxSize);
        this.setLocation(screenWidth / 2 - maxSize / 2, screenHeight / 2 - maxSize / 2);

        Constants.setWidth(maxSize);
        Constants.setHeight(maxSize);
    }

    public static MyPanel getInstance() {
        if (panelInstance == null) {
            panelInstance = new MyPanel();
            return panelInstance;
        }
        return panelInstance;
    }

    public void update() {
        logic.executeRequests();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintPieces(g);
    }

    private void paintPieces(Graphics g) {
        for (PuzzlePiece piece : logic.getPuzzlePieces()) {
            g.drawImage(piece.getImage(), piece.getLocation().getX(), piece.getLocation().getY(), (int) this.getSize().getWidth() / 3, (int) this.getSize().getHeight() / 3, null);
        }
    }

    public Board getLogic() {
        return logic;
    }
}
