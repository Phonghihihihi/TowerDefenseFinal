package towerdefense.component.enemy;

import towerdefense.component.GameEntity;
import towerdefense.util.Direction;
import towerdefense.util.Vector2;

import java.util.Vector;

public interface Enemy extends GameEntity {
    int getReward();

    void takeDamage(int damage);

    void move();

    Vector2 getPosition();

    boolean isDestroyed();

    Direction getDirection();


}
