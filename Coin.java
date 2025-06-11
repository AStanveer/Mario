import java.awt.*;

public class Coin {
    private int x, y;
    private static int score = 0;
    private boolean collected = false;
    private final int SIZE = 20;

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        if (!collected) {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, SIZE, SIZE);

        }
    }
    public static int getScore(){
        return score;
    }

    public void checkCollision(Player player) {
        if (
    !collected &&
    (
        (player.getBounds("down").intersects(new Rectangle(x, y, SIZE, SIZE))) ||
        (player.getBounds("right").intersects(new Rectangle(x, y, SIZE, SIZE))) ||
        (player.getBounds("top").intersects(new Rectangle(x, y, SIZE, SIZE))) ||
        (player.getBounds("left").intersects(new Rectangle(x, y, SIZE, SIZE)))
    )
) {
            collected = true;
            score++;
        }
    }
}