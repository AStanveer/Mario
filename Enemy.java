import java.awt.*;

import javax.swing.ImageIcon;

public class Enemy {
    private int x, y;
    private int width = 60, height = 80;
    private int direction = 1;
    private final int speed = 2;
    private int patrolDistance = 100;
    private int startX;
    private Image EnemyImg1,EnemyImg1Left;
    private boolean dead;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.startX = x;

        EnemyImg1 = new ImageIcon(getClass().getResource("./Enemy1.png")).getImage();
        EnemyImg1Left = new ImageIcon(getClass().getResource("./Enemy1Left.png")).getImage();
        
    }

    public void move() {
        x += direction * speed;
        if (x > startX + patrolDistance || x < startX - patrolDistance) {
            direction *= -1;
        }
    }

    public boolean checkCollision(Player player) {
    if (dead) return false;

    Rectangle playerBottom = player.getBounds("down");
    Rectangle playerRight = player.getBounds("right");
    Rectangle playerLeft = player.getBounds("left");

    // Player stomps the enemy
    if (playerBottom.intersects(getBounds("top"))) {
        dead = true;
        player.bounce();  // Player bounces up
        return false;     // Don't kill the player
    }

    if (
        playerBottom.intersects(getBounds("right")) ||
        playerBottom.intersects(getBounds("left")) ||
        playerBottom.intersects(getBounds("down")) ||
        playerLeft.intersects(getBounds("right")) ||
        playerLeft.intersects(getBounds("left")) ||
        playerLeft.intersects(getBounds("down")) ||
        playerRight.intersects(getBounds("right")) ||
        playerRight.intersects(getBounds("left")) ||
        playerRight.intersects(getBounds("down"))
    ) {
        return true; // Player loses
    }

    return false;
}

    public void draw(Graphics g) {
        if(!dead && direction == 1){
        g.drawImage(EnemyImg1, x, y, width, height, null);
        }
        if(!dead && direction == -1){
        g.drawImage(EnemyImg1Left, x, y, width, height, null);
        }
    }

    public Rectangle getBounds(String orientation) {
        if (orientation == "down"){
            return new Rectangle(x + width/4, y + height/2, width/2, height/2);
        }
        else if (orientation == "top"){
            return new Rectangle(x,y,width,height/4); 
        }
        else if (orientation == "right"){
            return new Rectangle(x+width-5,y+5,5,height-10 );
        }
        else if (orientation == "left"){
            return new Rectangle(x,y+5,5,height-10 );
        }
        else{
            return null;
        }
    }
}
