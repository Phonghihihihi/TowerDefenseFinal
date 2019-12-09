package towerdefense.component.tower;

import towerdefense.component.GameTile;
import towerdefense.component.bullet.Bullet;
import towerdefense.component.enemy.Enemy;

public interface Tower extends GameTile {
     Enemy getTarget();
     void setTarget(Enemy target);
     double distanceTo(double x, double y);
     double getAngleBetweenEnemy();
     double getSpeed();
     public double getRange();

     public double getDamage();
     //void rotateTower();
     boolean checkEnemyInRange(Enemy enemy);
     void upgrade();
     public int getIs_Bullet() ;

     public void setIs_Bullet(int is_bullet) ;
     public String image_Bullet();
     public Bullet getBullet();

}
