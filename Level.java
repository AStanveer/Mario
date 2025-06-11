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
        if(levelID == 1){
            
        }
        else if (levelID == 2){

        }
        else if(levelID == 3){

        }
        else if(levelID == 4){

        }

    }

    public void levels(int levelID){

        if (levelID == 1){
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
        else if (levelID == 2){

        }
        else if(levelID == 3){

        }
        else if(levelID == 4){

        }

    }
    public void draw(Graphics g) {
        g.setColor(new Color(200, 255, 200));
        g.fillRect(0, 0, 2000, 600);
    }
}

