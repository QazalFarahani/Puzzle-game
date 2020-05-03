package GUI;

import javax.swing.*;

public class MyFrame extends JFrame {
    private JPanel gamePanel;

    public MyFrame(JPanel panel){
        super("Puzzle");
        this.gamePanel = panel;
        this.init();
    }

    private void init(){
        this.setSize(gamePanel.getSize());
        this.setLocation(gamePanel.getLocation());
        this.add(gamePanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(new MyKeyListener());
        this.setVisible(true);
    }
}
