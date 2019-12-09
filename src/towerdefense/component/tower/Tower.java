package towerdefense.component.tower;

import javafx.scene.shape.Circle;
import towerdefense.component.GameTile;
import towerdefense.component.enemy.Enemy;

public interface Tower extends GameTile {
     Circle getCircle();
     void drawCircle();
     Enemy getTarget();
     int getPrice();
     void setTarget(Enemy target);
     double distanceTo(Enemy enemy);
     double getAngleBetweenEnemy();
     double getSpeed();
     public double getRange();

     public double getDamage();
     //void rotateTower();
     boolean checkEnemyInRange(Enemy enemy);
     void upgrade();

}
