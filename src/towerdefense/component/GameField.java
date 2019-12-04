package towerdefense.component;

import towerdefense.component.enemy.Enemy;
import towerdefense.component.enemy.NormalEnemy;

import java.util.ArrayList;
import java.util.List;

public class GameField {
    private int normalNumber = 5;
    private int enemyCounter = 0;
    private int spawnRate = 50;
    private int timer = 0;
    private boolean spawning = true;
    List<GameEntity> gameEntities = new ArrayList<GameEntity>(GameConfig.MAP_TILE);
    List<Enemy> Enemies = new ArrayList<Enemy>();

    public GameField()
    {}

    public int getEnemyCounter() {
        return enemyCounter;
    }
    public boolean isWaveOver()
    {
        return (gameEntities.isEmpty() && !spawning);
    }
    public void setEnemyCounter(int enemyCounter) {
        this.enemyCounter = enemyCounter;
    }

    public boolean isSpawning() {
        return spawning;
    }

    public void setSpawning(boolean isSpawning)
    {
        this.spawning = isSpawning;
    }
    public void spawnEnemies()
    {
        if (this.enemyCounter < normalNumber && spawning)
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
        else if (enemyCounter >= normalNumber)
        {
            spawning = false;
            enemyCounter = 0;
        }
    }

    public List<GameEntity> getGameEntities() {
        return gameEntities;
    }

    public void setGameEntities(List<GameEntity> gameEntities)
    {
        this.gameEntities = gameEntities;
    }

}
