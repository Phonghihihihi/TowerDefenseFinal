package towerdefense.component.tower;

import towerdefense.component.enemy.Enemy;

public interface Tower {
     double distanceToEnemy(Enemy enemy);
     boolean checkEnemyInRange(Enemy enemy);
}
