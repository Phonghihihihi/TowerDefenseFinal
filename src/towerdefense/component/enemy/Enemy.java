package towerdefense.component.enemy;

import towerdefense.component.GameEntity;
import towerdefense.util.Vector2;

public interface Enemy extends GameEntity {
    int getReward();

    void takeDamage(int damage);

    void move();

    Vector2 getPosition();

    boolean isDestroyed();




}
