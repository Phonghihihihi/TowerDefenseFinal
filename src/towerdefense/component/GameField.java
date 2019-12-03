package towerdefense.component;

import towerdefense.component.enemy.Enemy;
import towerdefense.component.enemy.NormalEnemy;

import java.util.ArrayList;
import java.util.List;

public class GameField {
    private int normalNumber = 5;
    private int enemyCounter = 0;
    private int spawnRate = 30;
    private int timer = 0;
    private boolean isSpawning = true;
    List<GameEntity> gameEntities = new ArrayList<GameEntity>(GameConfig.MAP_TILE);
    List<Enemy> Enemies = new ArrayList<Enemy>();

    public void refreshSpawner(){
        enemyCounter = 0;
    }

    public GameField()
    {}
    public void setSpawning(boolean isSpawning)
    {
        this.isSpawning = isSpawning;
    }

    public boolean isSpawning() {
        return isSpawning;
    }

    public void spawnEnemies()
    {
        if (this.enemyCounter <= normalNumber && isSpawning)
        {
            if (timer < spawnRate)
            {
                timer ++;
            }
            else
            {
                NormalEnemy e1 = new NormalEnemy(64, 640, 1, 10, 2, 3, 4, 8);
                gameEntities.add(e1);
                this.enemyCounter++;
                timer = 0;

            }
        }
        else if (enemyCounter > normalNumber)
        {
            setSpawning(false);
            refreshSpawner();
        }
    }




    public List<GameEntity> getGameEntities() {
        return gameEntities;
    }


}
