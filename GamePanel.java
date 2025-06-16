import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private final int DELAY = 16;
    private final int GAME_TIME_LIMIT = 300;
    private int timeLeft = GAME_TIME_LIMIT;
    private long lastTimerCheck = System.currentTimeMillis(); // 

    private Player player;
    private boolean left, right, jumping;

    private Level level;
    private int cameraX = 0;
    private int playerLives = 5;
    private boolean gameOver = false;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(200, 255, 200));
        setFocusable(true);
        addKeyListener(this);

        player = new Player(100, 300);
        timer = new Timer(DELAY, this);

        level = new Level(1); // Load level 1
    }

    public void startGame() {
        timer.start();
    }
    private void handlePlayerHit() {
    playerLives--;
    if (playerLives <= 0) {
        gameOver = true;
        timer.stop();
        repaint();
    } else {
        player.setX(100);
        player.setY(300);
    }
}

    @Override
public void actionPerformed(ActionEvent e) {
    if (!gameOver) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimerCheck >= 1000) { // 1 second passed
            timeLeft--;
            lastTimerCheck = currentTime;

            if (timeLeft <= 0) {
                handlePlayerHit(); // lose a life
                timeLeft = GAME_TIME_LIMIT; // reset timer
            }
        }
        player.move(left, right, jumping, level.getPlatforms());

        for (Coin c : level.getCoins()) {
            c.checkCollision(player);
        }

        for (Enemy enemy : level.getEnemies()) {
            enemy.move();
            if (enemy.checkCollision(player)) {
                handlePlayerHit();
                return;
            }
        }

        for (Obstacle obstacle : level.getObstacles()) {
            obstacle.update();
            if (obstacle.checkCollision(player)) {
                handlePlayerHit();
                return;
            }
        }

        cameraX = player.getX() - 400;
        repaint();
    }
}


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(-cameraX, 0);

        int screenWidth = getWidth();
        int screenHeight = getHeight();

        ArrayList<Image> backgroundImages = level.getBackgroundImages();
        int sectionWidth = screenWidth;

        int startIdx = Math.max(0, cameraX / sectionWidth - 1);
        int endIdx = Math.min(backgroundImages.size(), startIdx + 6);

        for (int i = startIdx; i < endIdx; i++) {
            int drawX = i * sectionWidth;
            g2d.drawImage(backgroundImages.get(i), drawX, 0, sectionWidth, screenHeight, null);
        }

        for (Platform p : level.getPlatforms()) p.draw(g2d);
        for (Coin c : level.getCoins()) c.draw(g2d);
        for (Enemy e : level.getEnemies()) e.draw(g2d);
        for (Obstacle o : level.getObstacles()) o.draw(g2d);

        player.draw(g2d, left, right, jumping);
        g2d.dispose();

        if (gameOver) {
    g.setColor(Color.RED);
    g.setFont(new Font("Arial", Font.BOLD, 48));
    g.drawString("You have lost!", 250, 280);
    g.drawString("Game Over!", 280, 340);
} else {
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 25));
    g.drawString("Merits: " + Coin.getScore(), 10, 20);
    g.drawString("Lives: " + playerLives, 10, 50);
    
    // Convert seconds to MM:SS format
    int minutes = timeLeft / 60;
    int seconds = timeLeft % 60;
    String timeString = String.format("Time: %02d:%02d", minutes, seconds);
    g.drawString(timeString, 650, 20); // adjust x/y for top right corner
}
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> left = true;
            case KeyEvent.VK_RIGHT -> right = true;
            case KeyEvent.VK_SPACE -> jumping = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> left = false;
            case KeyEvent.VK_RIGHT -> right = false;
            case KeyEvent.VK_SPACE -> jumping = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
