package towerdefense.component.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import towerdefense.component.AbstractEntity;
import towerdefense.component.TileMap;
import towerdefense.ui.TowerDefense;

import static towerdefense.component.GameConfig.TILE_SIZE;

public abstract class AbstractEnemy extends AbstractEntity implements Enemy {

    protected int health;
    protected int armor;
    protected int reward;
    protected int speed;
    protected ImageView enemyV ;


    private boolean destroyed = false;

    public AbstractEnemy(double posX, double posY, double width, double height, int health, int armor, int reward, int speed) {
        super(posX, posY, width, height);
        this.health = health;
        this.armor = armor;
        this.reward = reward;
        this.speed = speed;
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

    public ImageView getEnemyV() {
        return enemyV;
    }

    @Override
    public void takeDamage(int damage) {
        health -= Math.max((damage - armor), 0);
        if (health<=0){
            destroyed = true;
            Destroy();
        }

    }

    public void Destroy(){

    };
    public void move (int[][] path) {
        double speedX = 0;
        double speedY = 0;
        int tile_Y = (int) (this.getPosX() / TILE_SIZE);
        int tile_X = (int) (this.getPosY() / TILE_SIZE);
        if (this.getPosX() < 1230) {
            if (path[tile_X][tile_Y] == 8) {
                speedY = -this.getSpeed();
            } else if (path[tile_X][tile_Y] == 2) {
                speedY = this.getSpeed();
            } else if (path[tile_X][tile_Y] == 4) {
                speedX = -this.getSpeed();

            } else if (path[tile_X][tile_Y] == 6) {
                speedX = this.getSpeed();
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
        enemyV.relocate(this.getPosX(), this.getPosY());
        if (this.getPosX() > 1210)
        {
            TowerDefense.root.getChildren().remove(enemyV);
        }
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }




}
