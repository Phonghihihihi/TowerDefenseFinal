package towerdefense.component.enemy;

import towerdefense.component.GameEntity;


public interface Enemy extends GameEntity {
    int getReward();

    void takeDamage(int damage);

    void move();

    void destroyEnemy();


    boolean isDestroyed();




}
