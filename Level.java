import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Level {
    private int levelID;
    private ArrayList<Platform> platforms;
    private ArrayList<Coin> coins;
    private ArrayList<Enemy> enemies;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Image> backgroundImages;

    public Level(int levelID) {
        this.levelID = levelID;
        platforms = new ArrayList<>();
        coins = new ArrayList<>();
        enemies = new ArrayList<>();
        obstacles = new ArrayList<>();
        backgroundImages = new ArrayList<>();

        switch (levelID) {
            case 1: {
                loadLevel1();
            }
            case 2: {
                loadLevel2();
            }
            case 3: {
                loadLevel3();
            }
            case 4: {
                loadLevel4();
            }
        }
    }

    private void loadLevel1() {
        platforms.add(new Platform(0, 500, 4000, 350, false));
        platforms.add(new Platform(300, 400, 200, 20, true));
        platforms.add(new Platform(600, 340, 200, 20, true));
        platforms.add(new Platform(950, 420, 150, 20, true));

        coins.add(new Coin(350, 380));
        coins.add(new Coin(650, 320));

        enemies.add(new Enemy(1000, 360));
        enemies.add(new Enemy(1200, 440));
        enemies.add(new Enemy(1800, 440));

        obstacles.add(new Obstacle(1600, 450));

        backgroundImages.add(new ImageIcon(getClass().getResource("/Library.png")).getImage());
        backgroundImages.add(new ImageIcon(getClass().getResource("/World1.png")).getImage());
        backgroundImages.add(new ImageIcon(getClass().getResource("/World2.png")).getImage());
        backgroundImages.add(new ImageIcon(getClass().getResource("/Library.png")).getImage());
        backgroundImages.add(new ImageIcon(getClass().getResource("/World2.png")).getImage());
        backgroundImages.add(new ImageIcon(getClass().getResource("/World1.png")).getImage());
    }
    public void loadLevel2(){

    }
    public void loadLevel3(){

    }
    public void loadLevel4(){

    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public ArrayList<Image> getBackgroundImages() {
        return backgroundImages;
    }
}

        g.fillRect(0, 0, 2000, 600);
    }
}

