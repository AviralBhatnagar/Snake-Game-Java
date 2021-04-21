import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class scoreBoard extends JPanel
{
    private JLabel label;
    private int score = 0;
    public scoreBoard(){
        setBackground(Color.white);
        label = new JLabel("Score: "+Integer.toString(score));
        add(label);
    }
    
    public void increementScore(){
        label.setText("Score : "+Integer.toString(++score));
    }

    public int getScore(){
        return score;
    }

    public void setScore(){
        score=0;
        label.setText("Score : "+Integer.toString(++score));
    }
}