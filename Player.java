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

    
    private Image[] rightFrames;
    private Image[] leftFrames;
    private Image jumpingImg;
    private int currentFrame = 0;
    private long lastFrameTime = 0;
    private final int frameDelay = 100; 

    public Player(int x, int y) {
        this.x = x;
        this.y = y;

        rightFrames = new Image[] {
            new ImageIcon(getClass().getResource("./Student.png")).getImage(),
            new ImageIcon(getClass().getResource("./Student2.png")).getImage()
        };

        leftFrames = new Image[] {
            new ImageIcon(getClass().getResource("./StudentLeft.png")).getImage(),
            new ImageIcon(getClass().getResource("./Student2Left.png")).getImage()
        };

        jumpingImg = new ImageIcon(getClass().getResource("./Jumping.png")).getImage();
    }

    public void move(boolean left, boolean right, boolean jumping, ArrayList<Platform> platforms) {
        previousY = y;
        if (left) {
            velX = -speed;
        }
        else if (right) {
            velX = speed;
        }
        else velX = 0;

        if (jumping && onGround) {
            velY = jumpStrength;
            onGround = false;
        }

        if (left || right) updateAnimationFrame();
        else currentFrame = 0;

        velY += gravity;
        x += velX;
        y += velY;

        onGround = false;
        for (Platform p : platforms) {
            boolean horizontallyAligned = x + width > p.getX() && x < p.getX() + p.getWidth();
            boolean isFallingOntoPlatform = previousY + height <= p.getY() && y + height >= p.getY();

            if (horizontallyAligned && isFallingOntoPlatform) {
                y = p.getY() - height;
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

    private void updateAnimationFrame() {
        long now = System.currentTimeMillis();
        if (now - lastFrameTime > frameDelay) {
            currentFrame = (currentFrame + 1) % rightFrames.length;
            lastFrameTime = now;
        }
    }

    public void setX(int newX) { x = newX; }
    public int getX() { return x; }
    public void setY(int newY) { y = newY; }
    public void setVelY(int newVelY) { velY = newVelY; }
    public void bounce() { y -= 15; }
    public boolean isJumping() { return velY < 0; }

    public void hitHead(int platformBottomY) {
        y = platformBottomY;
        velY = 0;
    }

    public void draw(Graphics g, boolean right, boolean left, boolean jumping) {
        if (jumping) {
            g.drawImage(jumpingImg, x, y, width, height, null);
        } else if (left) {
            g.drawImage(rightFrames[currentFrame], x, y, width, height, null);
        } else if (right) {
            g.drawImage(leftFrames[currentFrame], x, y, width, height, null);
        } else {
            g.drawImage(rightFrames[0], x, y, width, height, null); // idle default
        }
    }

    public Rectangle getBounds(String orientation) {
        switch (orientation) {
            case "down":
                return new Rectangle(x + 10, y + height - 5, width - 20, 5);
            case "top":
                return new Rectangle(x + 10, y, width - 20, 5);
            case "right":
                return new Rectangle(x + width - 5, y + 10, 5, height - 20);
            case "left":
                return new Rectangle(x, y + 10, 5, height - 20);
            case "all":
                return new Rectangle(x, y, width, height);
            default:
                return null;
        }
    }
}
