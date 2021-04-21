import java.util.Random;

public class Food {
    private Coord coord;
    private Random random;

    public Food(){
        random = new Random();
        coord = new Coord(random.nextInt(490)/10*10,(random.nextInt(470)+20)/10*10);
    }

    public int getX(){
        return coord.getX();
    }

    public int getY(){
        return coord.getY();
    }

    public void setX(int x){
        coord.setX(x);
    }

    public void setY(int y){
        coord.setY(y);
    }

    public void update(int x,int y){
        this.setX(x);
        this.setY(y);
    }
}
