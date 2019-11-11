package towerdefense.component.enemy;

import javafx.scene.image.Image;
import towerdefense.component.AbstractEntity;
import towerdefense.util.Direction;
import towerdefense.util.Vector2;

public abstract class AbstractEnemy extends AbstractEntity implements Enemy {
    private Vector2 pos;
    private Vector2 target;
    private int health;
    private int armor;
    private int reward;
    private int speed;
    private Direction direction;

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

    @Override
    public Vector2 getPosition() {
        return pos;
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

    public void move(double x, double y){
        pos.setX(pos.getX() + x * speed);
        pos.setY(pos.getY() + y * speed);
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }
}
