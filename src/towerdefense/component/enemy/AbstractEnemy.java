package towerdefense.component.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.component.AbstractEntity;
import towerdefense.component.TileMap;
import towerdefense.util.Vector2;

import static towerdefense.component.CommonFunc.TILE_SIZE;

public abstract class AbstractEnemy extends AbstractEntity implements Enemy {

    private Vector2 target;
    private int health;
    private int armor;
    private int reward;
    private int speed;
    private ImageView enemyV = new ImageView(image);


    private boolean destroyed = false;

    public AbstractEnemy(Vector2 position, double width, double height, Image image, int health, int armor, int reward, int speed) {
        super(position, width, height, image);
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
        int tile_Y = (int) (this.getPosition().getX() / TILE_SIZE);
        int tile_X = (int) (this.getPosition().getY() / TILE_SIZE);
        if (path[tile_X][tile_Y] == 8) {
            speedY = -this.getSpeed();
        } else if (path[tile_X][tile_Y] == 2) {
            speedY = this.getSpeed();
        } else if (path[tile_X][tile_Y] == 4) {
            speedX = -this.getSpeed();

        } else if (path[tile_X][tile_Y] == 6) {
            speedX = this.getSpeed();
        }
        this.getPosition().setX(this.getPosition().getX() + speedX);
        this.getPosition().setY(this.getPosition().getY() + speedY);
    }
    public void update()
        {

            this.move(TileMap.MAP_PATH);
        }
    public void render(GraphicsContext graphicsContext)
    {
        if (this.getPosition().getX() < 1216)
        {
            enemyV.relocate(this.getPosition().getX(), this.getPosition().getY());
        }
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }




}
