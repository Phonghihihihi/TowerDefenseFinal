package towerdefense.component.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import towerdefense.component.AbstractEntity;
import towerdefense.component.GameConfig;
import towerdefense.component.TileMap;
import towerdefense.ui.TowerDefense;

import static towerdefense.component.GameConfig.TILE_SIZE;

public abstract class AbstractEnemy extends AbstractEntity implements Enemy {

    protected int health;
    protected int armor;
    protected int reward;
    protected int speed;


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

    @Override
    public void destroyEnemy() {
        TowerDefense.root.getChildren().remove(enemyV);
    }


    public void move (int[][] path) {
        double speedX = 0;
        double speedY = 0;
        int tile_Y = (int) (this.getPosX() / TILE_SIZE);
        int tile_X = (int) (this.getPosY() / TILE_SIZE);
        if (this.getPosX() < (GameConfig.GAME_WIDTH - GameConfig.TILE_SIZE/2.0)) {
            if (path[tile_X][tile_Y] == 8) {
                speedY = -this.getSpeed();
                imageV.setRotate(-90);
            } else if (path[tile_X][tile_Y] == 2) {
                speedY = this.getSpeed();
                imageV.setRotate(90);
            } else if (path[tile_X][tile_Y] == 4) {
                speedX = -this.getSpeed();
                imageV.setRotate(-180);
            } else if (path[tile_X][tile_Y] == 6) {
                speedX = this.getSpeed();
                imageV.setRotate(0);
            }
            this.setPosX(this.getPosX() + speedX);
            this.setPosY(this.getPosY() + speedY);
        }

    }
    public void update()
        {
            this.move(TileMap.MAP_PATH);
        }
    public void render(GraphicsContext graphicsContext)
    {
        imageV.relocate(this.getPosX(), this.getPosY());
        if (this.getPosX() > (GameConfig.GAME_WIDTH - GameConfig.TILE_SIZE/2.0 -20))
        {
            TowerDefense.root.getChildren().remove(imageV);
        }
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }




}
