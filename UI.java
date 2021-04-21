import java.awt.BorderLayout;
import javax.swing.JFrame;

public class UI {
    private static JFrame jFrame;
    public static void main(String args[]){
        jFrame = new JFrame("Snake");
        scoreBoard sBoard = new scoreBoard();
        jFrame.add(sBoard,BorderLayout.NORTH);
        jFrame.add(new gameBoard(sBoard),BorderLayout.CENTER);
        jFrame.setVisible(true);
        jFrame.setSize(500,560);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}