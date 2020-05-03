import GUI.MyFrame;
import GUI.MyPanel;

import javax.swing.*;

public class Game implements Runnable{
    private MyFrame frame;
    private MyPanel panel;
    private Thread thread;

    public Game(){

    }

    public void startGame(){
        init();
        thread = new Thread(this);
        thread.start();
    }

    private void init(){

        panel = MyPanel.getInstance();
        frame = new MyFrame(panel);

        if (!panel.getLogic().isSolvable()){
            JOptionPane.showMessageDialog(frame, "this puzzle is not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
            panel.getLogic().setGameFinished(true);
        }

    }

    public void run(){
        while (true) {
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            panel.repaint();
            panel.update();
            frame.repaint();

            if (panel.getLogic().isGameFinished()) {
                break;
            }
            if (panel.getLogic().getGameState().equals("finished")) {
                JOptionPane.showMessageDialog(frame, "You finished the game, congratulation", "game finished", JOptionPane.INFORMATION_MESSAGE);
                panel.getLogic().setGameFinished(true);
            }

        }
    }

}
