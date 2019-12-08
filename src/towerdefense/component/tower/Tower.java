package towerdefense.component.tower;

import towerdefense.component.GameTile;
import towerdefense.component.enemy.Enemy;

public interface Tower extends GameTile {
     Enemy getTarget();
     void setTarget(Enemy target);
     double distanceTo(double x, double y);
     double getAngleBetweenEnemy();
     //void rotateTower();
     boolean checkEnemyInRange(Enemy enemy);
     void upgrade();

}
