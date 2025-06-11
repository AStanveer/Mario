import java.awt.*;

public class Obstacle {
    private int x,y; 
    private int size = 30; 
    private boolean visible = true; 
    private int timer = 0;

    public Obstacle(int x,int y){
        this.x = x; 
        this.y = y;
    }

    public void update(){
        timer++; 
        if (timer % 100 ==0 ){
            visible = !visible; 
        }
    }

    public void draw(Graphics g){
        if (visible){
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, size, size);
        }
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,size,size);
    }

    public boolean checkCollision(Player p){
        if((p.getBounds("all").intersects(getBounds())) && visible ){
            return true;
        }
        else{
            return false;
        }
    }

    
}
