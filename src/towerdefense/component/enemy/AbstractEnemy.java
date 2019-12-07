package towerdefense.component.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import towerdefense.component.AbstractEntity;
import towerdefense.component.GameConfig;
import towerdefense.component.TileMap;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;

import java.util.Map;


public abstract class AbstractEnemy extends AbstractEntity implements Enemy {

    protected int health;
    protected int armor;
    protected int reward;
    protected int speed;

    private double speedX = 0;
    private double speedY = -this.getSpeed();



    private boolean destroyed = false;

    public AbstractEnemy(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
//        this.health = health;
//        this.armor = armor;
//        this.reward = reward;
//        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public int getArmor() {
        return armor;
    }

    public int getHealth() {
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
            System.out.println(this.posX + " " + this.posY);
        }

    }
    public void update() {
            this.move(TileMap.MAP_PATH);
        }
    public void render(GraphicsContext graphicsContext)
    {
        imageV.relocate(this.getPosX() - GameConfig.TILE_SIZE/2.0, this.getPosY() - GameConfig.TILE_SIZE/2.0);
        if (this.getPosX() > (GameConfig.GAME_WIDTH - GameConfig.TILE_SIZE/2.0 -20))
        {
            Game.root.getChildren().remove(imageV);
        }
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }




}
