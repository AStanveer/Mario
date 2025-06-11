import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private final int DELAY = 16; // ~60 FPS

    private Player player;
    private boolean left, right, jumping;

    private ArrayList<Platform> platforms;
    private ArrayList<Coin> coins;
    private ArrayList<Enemy> enemies;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Image> backgroundImages;
    private Level level;
    private int cameraX = 0;
    private boolean gameOver = false;
    Image backgroundImage;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(200, 255, 200));
        setFocusable(true);
        addKeyListener(this);

        player = new Player(100, 300);
        timer = new Timer(DELAY, this);

        platforms = new ArrayList<>();
        platforms.add(new Platform(0, 500, 4000, 350,false));
        platforms.add(new Platform(300, 400, 200, 20,true));
        platforms.add(new Platform(600, 340, 200, 20,true));
        platforms.add(new Platform(950, 420, 150, 20,true));

        coins = new ArrayList<>();
        coins.add(new Coin(350, 380));
        coins.add(new Coin(650, 320));

        enemies = new ArrayList<>();
        enemies.add(new Enemy(1000, 360));
        enemies.add(new Enemy(1200, 440));
        enemies.add(new Enemy(1800, 440));

        obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(1600, 450));

        backgroundImages = new ArrayList<>();
        backgroundImages.add(new ImageIcon(getClass().getResource("/Library.png")).getImage());
        backgroundImages.add(new ImageIcon(getClass().getResource("/World1.png")).getImage());
        backgroundImages.add(new ImageIcon(getClass().getResource("/World2.png")).getImage());
        backgroundImages.add(new ImageIcon(getClass().getResource("/Library.png")).getImage());
        backgroundImages.add(new ImageIcon(getClass().getResource("/World2.png")).getImage());
        backgroundImages.add(new ImageIcon(getClass().getResource("/World1.png")).getImage());
    }

    public void startGame() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameOver){
            player.move(left, right, jumping, platforms);
            for (Coin c : coins) {
                c.checkCollision(player);
            }
            for (Enemy enemy : enemies) {
                enemy.move();
                if(enemy.checkCollision(player)){
                    gameOver = true; 
                    timer.stop();
                }
            }
            for (Obstacle obstacle : obstacles) {
                obstacle.update();
                if(obstacle.checkCollision(player)){
                    gameOver = true;
                    timer.stop();
                }}
            cameraX = player.getX() - 400;
            repaint();
        
    }
}


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(-cameraX, 0);

        level.draw(g2d);
        int screenWidth = getWidth(); // should be 800
        int screenHeight = getHeight();

        int sectionWidth = screenWidth; // 800px per image
        int startIdx = Math.max(0, cameraX / sectionWidth - 1); // preload one before
        int endIdx = Math.min(backgroundImages.size(), startIdx + 6); // and one after

        for (int i = startIdx; i < endIdx; i++) {
            int drawX = i * sectionWidth;
            g2d.drawImage(backgroundImages.get(i), drawX, 0, sectionWidth, screenHeight, null);
        }
        for (Platform p : platforms) p.draw(g2d);
        for (Coin c : coins) c.draw(g2d);
        for (Enemy e : enemies) e.draw(g2d);
        for (Obstacle o : obstacles) o.draw(g2d);

        player.draw(g2d,left,right,jumping);
        g2d.dispose();
        if(gameOver){
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over!", 280,300);
        }else{
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString("Merits: "+ Coin.getScore() + ".", 10,20);
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