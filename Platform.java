import java.awt.*;

public class Platform {
    private int x, y, width, height;
    private boolean visible = true; // default to visible

    public Platform(int x, int y, int width, int height, boolean visible) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visible = visible;
    }

    public void draw(Graphics g) {
        if (!visible) return;
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    

    public boolean checkCollision(Player player) {
        Rectangle playerBounds = player.getBounds("top");
        Rectangle platformBounds = getBounds();

        return playerBounds.intersects(platformBounds);
    }
}