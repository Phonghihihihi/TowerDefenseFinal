package towerdefense.component.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import towerdefense.component.AbstractEntity;
import towerdefense.component.GameConfig;
import towerdefense.component.TileMap;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;

import java.io.File;
import java.util.Map;


public abstract class AbstractEnemy extends AbstractEntity implements Enemy {


    protected int health;
    protected int armor;
    protected int reward;
    protected int speed;
<<<<<<< HEAD
    protected MediaPlayer attack = new MediaPlayer(new Media(new File("src/Assets/Music/Attack.mp3").toURI().toString()));
=======
    protected   int ENEMY_HEALTH;
>>>>>>> 1960f2d11b84ecae66278d41886638a964e51f1d

    private double speedX = 0;
    private double speedY = -this.getSpeed();
    private boolean destroyed = false;

    public AbstractEnemy() {
        super(GameConfig.SPAWN_X, GameConfig.SPAWN_Y);
    }

    public int getSpeed() {
        return speed;
    }

    public int getArmor() {
        return armor;
    }

    public double getHealth() {
        return health;
    }

    @Override
    public void takeDamage(int damage) {
        health -= Math.max((damage - armor), 0);
        if (health<=0){
            destroyed = true;
            destroyEnemy();
        }

    }
    public void attack()
    {
        attack.play();
    }

    public void destroyEnemy() {
        Game.root.getChildren().remove(imageV);
    }

    private double distanceToWayPoint(int tile_X, int tile_Y){
        return Math.sqrt((tile_X-this.getPosX())*(tile_X-this.getPosX()) + (tile_Y-this.getPosY())*(tile_Y-this.getPosY()));
    }


    public void move (int[][] path) {

        int tile_Y = (int) (this.getPosX() / GameConfig.TILE_SIZE) ;
        int tile_X = (int) (this.getPosY() / GameConfig.TILE_SIZE) ;
        if (this.getPosX() < (GameConfig.GAME_WIDTH)) {
            if (path[tile_X][tile_Y] == 8 && distanceToWayPoint(tile_Y*64 + 32, tile_X*64 + 32) < 4) {
                speedY = -this.getSpeed();
                speedX = 0;
                imageV.setRotate(-90);
            } else if (path[tile_X][tile_Y] == 2 && distanceToWayPoint(tile_Y*64 + 32, tile_X*64 + 32) < 4) {
                speedY = this.getSpeed();
                speedX = 0;
                imageV.setRotate(90);
            } else if (path[tile_X][tile_Y] == 4 && distanceToWayPoint(tile_Y*64 + 32, tile_X*64 +32) < 4) {
                speedX = -this.getSpeed();
                speedY = 0;
                imageV.setRotate(-180);
            } else if (path[tile_X][tile_Y] == 6 && distanceToWayPoint(tile_Y*64 + 32, tile_X*64 + 32) < 4) {
                speedX = this.getSpeed();
                speedY = 0;
                imageV.setRotate(0);
            }
            this.setPosX(this.getPosX() + speedX);
            this.setPosY(this.getPosY() + speedY);
        }

    }
    public void update() {
            this.move(TileMap.MAP_PATH);
        }
    public void render(GraphicsContext graphicsContext)
    {
        imageV.relocate(this.getPosX() - GameConfig.TILE_SIZE/2.0, this.getPosY() - GameConfig.TILE_SIZE/2.0);
        if (this.getPosX() >= GameConfig.GAME_WIDTH - 10)
        {
            Game.root.getChildren().remove(imageV);
        }
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }




}
