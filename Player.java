import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player {
    private int x, y;
    private int previousY;
    private int width = 60, height = 80;
    private int velX = 0, velY = 0;
    private final int speed = 5;
    private final int gravity = 1;
    private final int jumpStrength = -15;
    private boolean onGround = false;
    Timer gameLoop;
    Timer playerTimer;

    //Player Graphics
    public Image playerImg; 
    public Image jumImg;
    public Image playerLeftImg;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        playerImg = new ImageIcon(getClass().getResource("./Student.png")).getImage();
        jumImg = new ImageIcon(getClass().getResource("./Jumping.png")).getImage();
        playerLeftImg = new ImageIcon(getClass().getResource("./StudentLeft.png")).getImage(); 
        
        
    }

    public void move(boolean left, boolean right, boolean jumping, ArrayList<Platform> platforms) {
        previousY = y;
        if (left) velX = -speed;
        else if (right) velX = speed;
        else velX = 0;

        if (jumping && onGround) {
            velY = jumpStrength;
            onGround = false;
        }

        velY += gravity;
        x += velX;
        y += velY;

        onGround = false;
        for (Platform p : platforms) {
            // Check horizontal overlap
            boolean horizontallyAligned = x + width > p.getX() && x < p.getX() + p.getWidth();

            // Check if player was above the platform last frame and is now touching it
            boolean isFallingOntoPlatform = previousY + height <= p.getY() && y + height >= p.getY();

            if (horizontallyAligned && isFallingOntoPlatform) {
                y = p.getY() - height; // Snap player to platform top
                velY = 0;
                onGround = true;
            }
            }

        if (y >= 500) {
            y = 500;
            velY = 0;
            onGround = true;
        }
    }
    public int getX(){
        return x;
    }
    public void bounce(){
        y -= 15;
    }

    public void draw(Graphics g, boolean right,boolean left, boolean jumping) {
        if(jumping){
            g.drawImage(jumImg, x, y, width, height, null);
        }
        if(left){
            g.drawImage(playerImg, x, y, width, height, null);
        }
        if(right){
            g.drawImage(playerLeftImg, x, y, width, height, null);
        }
        else {g.drawImage(playerImg, x, y, width, height, null);
        }
    }

    public Rectangle getBounds(String orientation) {
        if (orientation == "down"){
            return new Rectangle(x + 10, y + height -5, width-20, 5);
        }
        else if (orientation == "top"){
            return new Rectangle(x+10,y,width-20,5 ); 
        }
        else if (orientation == "right"){
            return new Rectangle(x + width - 5, y + 10, 5, height - 20);
        }
        else if (orientation == "left"){
            return new Rectangle(x, y + 10, 5, height - 20);
        }
        else if (orientation == "all"){
            return new Rectangle(x,y,width,height);
        }
        else{
            return null;
        }
    }
}
