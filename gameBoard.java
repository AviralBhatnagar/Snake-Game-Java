import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

public class gameBoard extends JPanel implements Runnable, KeyListener{

    private Thread t;
    private Random random;
    private int velocityX , velocityY, tick;
    private boolean moveX , moveY;
    private Snake snake;
    private Food food;
    private scoreBoard bScoreBoard ;

    public gameBoard(scoreBoard bScoreBoard){
        setBackground(new Color(175,255,102));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        init(bScoreBoard);
    }

    private void init(scoreBoard bScoreBoard){
        random = new Random();
        snake = new Snake();
        food = new Food();
        this.bScoreBoard = bScoreBoard;
        velocityX = 10;
        velocityY = 10;
        moveX = true;
        moveY = false;
        tick = 100;
        t = new Thread(this);
        t.start();
    }

    public void run()
    {
        while(true)
        {
            if(checkCollision()){
                if(tick>85){
                    tick--;
                }
                bScoreBoard.increementScore();
                snake.hasEat();
                updateFood();
            }
            checkBound();
            boolean isMoveValid = true;
            if(moveX) isMoveValid = snake.updateBy(velocityX, 0);
            else isMoveValid = snake.updateBy(0, velocityY);

            if(!isMoveValid){
                break;
            }
            repaint();
            try{Thread.sleep(tick);}
            catch(Exception e){System.out.println(e.toString());}
        }
        stopGame();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.red);
        g.fillOval(food.getX(),food.getY(),10,10);
        for(Coord x : snake.getCoordinates()){
            g.setColor(Color.BLACK);
            g.drawRect(x.getX(),x.getY(),10,10);
            g.setColor(Color.YELLOW);
            g.fillRect(x.getX()+1,x.getY()+1,9,9);
        }
    }

    private void stopGame(){
        JOptionPane.showMessageDialog(this,"Score : "+bScoreBoard.getScore(),"GameOver",JOptionPane.INFORMATION_MESSAGE);
    }
    private boolean checkCollision(){
        if(snake.getTopX() == food.getX() && snake.getTopY() == food.getY())
        return true;

        return false;
    }

    private void updateFood(){
        food.update(random.nextInt(490)/10*10,(random.nextInt(440)+50)/10*10);
    }

    private void checkBound(){
        if(snake.getTopX() > 500)
        snake.move(0,snake.getTopY());
        else if(snake.getTopX() < 0)
        snake.move(500,snake.getTopY());
        else if(snake.getTopY() > 500)
        snake.move(snake.getTopX(),0);
        else if(snake.getTopY() < 0)
        snake.move(snake.getTopX(),500);
    }
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP && !moveY)
        {
            moveX = false;
            moveY = true;
            velocityY = -Math.abs(velocityY);
        }
        else if(code == KeyEvent.VK_DOWN && !moveY)
        {
            moveY = true;
            moveX = false;
            velocityY = Math.abs(velocityY);
        }
        else if(code == KeyEvent.VK_RIGHT && !moveX){
            moveX = true;
            moveY = false;
            velocityX = Math.abs(velocityX);
        }
        else if(code == KeyEvent.VK_LEFT && !moveX){
            moveX = true;
            moveY = false;
            velocityX = -Math.abs(velocityX);
        }
    }
    public void keyReleased(KeyEvent e)
    {

    }
    public void keyTyped(KeyEvent e)
    {

    }
}