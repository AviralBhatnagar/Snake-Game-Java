import java.util.*;


public class Snake {

    private LinkedList<Coord> body;
    private Random random;
    private int snakeLength;

    public Snake()
    {
        body = new LinkedList<Coord>();
        random = new Random();
        snakeLength = 3;
        makeSnake();
    }

    public void makeSnake(){
        this.move(random.nextInt(490)/10*10,(random.nextInt(470)+20)/10*10);
        updateBy(10, 0);
        updateBy(10, 0);
    }

    public boolean move(int snakeX , int snakeY){
        if(!isCoordNew(snakeX,snakeY)){
            return false;
        }
        body.add(new Coord(snakeX , snakeY));
        if(body.size()>snakeLength)
        body.removeFirst();
        return true;
    }
    public boolean updateBy(int dx, int dy){
        return move(getTopX()+dx,getTopY()+dy);
    }
    public int getTopX(){
        return body.getLast().getX();
    }
    public int getTopY(){
        return body.getLast().getY();
    }
    public LinkedList<Coord> getCoordinates(){
        return body;
    }
    public void hasEat(){
        snakeLength += 1;
    }
    private boolean isCoordNew(int snakeX, int snakeY){
        for(Coord coord:getCoordinates()){
            if(coord.getX()==snakeX && coord.getY()==snakeY){
                return false;
            }
        }
        return true;
    }
}
