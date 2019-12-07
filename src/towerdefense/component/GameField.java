package towerdefense.component;

import towerdefense.component.enemy.*;
import towerdefense.component.tower.Tower;

import java.util.ArrayList;
import java.util.List;

public class GameField {
    private int normalNumber = GameConfig.NORMAL_ENEMY_WAVE_NUMBER;
    private int smallerNumber = 0;
    private int tankerNumber = 0;
    private int bossNumber = 0;
    private int enemyNumber = 0;
    private int enemyCounter = 0;
    private int spawnRate = GameConfig.SPAWN_RATE;
    private int timer = 0;
    private int waveCount = 0;
    private boolean isSpawning = false;
    private boolean placingNormalTower = false;
    private boolean placingMachineGunTower = false;
    private boolean upgradingTower = false;
    private boolean sellingTower = false;
    //List<GameEntity> gameEntities = new ArrayList<GameEntity>(GameConfig.MAP_TILE);
    Reinforcements reinforcements = new Reinforcements(0, 400, 64, 64);
    List<Enemy> enemies = new ArrayList<Enemy>();
    List<Tower> towers = new ArrayList<Tower>();

    public GameField(){}

    public int getWaveCount() {
        return waveCount;
    }

    public void setWaveCount() {
        this.waveCount++;
        System.out.println(waveCount);
    }

    public boolean isWaveOver()
    {
        return enemies.isEmpty() && !isSpawning;
    }

    public void setSpawning(boolean isSpawning)
    {
        this.isSpawning = isSpawning;
    }

    public boolean isSpawning() {
        return isSpawning;
    }

    public boolean isPlacingNormalTower() {
        return placingNormalTower;
    }

    public void setPlacingNormalTower(boolean placingNormalTower)
    {
        this.placingNormalTower = placingNormalTower;
    }

    public boolean isPlacingMachineGunTower()
    {
        return placingMachineGunTower;
    }

    public void setPlacingMachineGunTower(boolean placingMachineGunTower)
    {
        this.placingMachineGunTower = placingMachineGunTower;
    }

    public boolean isUpgradingTower() {
        return upgradingTower;
    }

    public void setUpgradingTower(boolean upgradingTower) {
        this.upgradingTower = upgradingTower;
    }

    public boolean isSellingTower() {
        return sellingTower;
    }

    public void setSellingTower(boolean sellingTower) {
        this.sellingTower = sellingTower;
    }

    public void refreshSpawner(){
        enemyCounter = 0;
    }

    public void resetTimer()
    {
        timer = 0;
    }

    public Reinforcements getReinforcements() {
        return reinforcements;
    }

    public void calculateWavePower()
    {
        normalNumber++;
        if (waveCount % 2 == 0 && waveCount > 3)
        {
            smallerNumber++;
        }
        if (waveCount == 3)
        {
            smallerNumber = GameConfig.SMALLER_ENEMY_WAVE_NUMBER;
        }
        if (waveCount % 5 == 0 && waveCount > 5)
        {
            tankerNumber++;
        }
        if (waveCount == 5)
        {
            tankerNumber = GameConfig.TANKER_ENEMY_WAVE_NUMBER;
        }
        if (waveCount % 10 == 0  && waveCount > 10)
        {
            bossNumber++;
        }
        if (waveCount == 10)
        {
            bossNumber = GameConfig.BOSS_ENEMY_WAVE_NUMBER;
        }
        enemyNumber = normalNumber + smallerNumber + tankerNumber + bossNumber;
    }
    public void spawnEnemies()
    {
        if (enemyCounter < enemyNumber && isSpawning)
        {
            for (int i=0; i < normalNumber; i++)
            {
                if (timer < spawnRate)
                {
                    timer++;
                }
                else
                {
                    Enemy enemy = new NormalEnemy();
                    enemies.add(enemy);
                    enemyCounter++;
                    resetTimer();
                }
            }
            if (waveCount >= 3)
            {
                for (int i=0; i < smallerNumber; i++)
                {
                    if (timer < spawnRate)
                    {
                        timer++;
                    }
                    else
                    {
                        Enemy enemy = new SmallerEnemy();
                        enemies.add(enemy);
                        enemyCounter++;
                        resetTimer();
                    }
                }
            }
            if (waveCount >= 5)
            {
                for (int i=0; i < tankerNumber; i++)
                {
                    if (timer < spawnRate)
                    {
                        timer++;
                    }
                    else
                    {
                        Enemy enemy = new TankerEnemy();
                        enemies.add(enemy);
                        enemyCounter++;
                        resetTimer();
                    }
                }
            }
            if (waveCount >= 10)
            {
                for (int i=0; i < bossNumber; i++)
                {
                    if (timer < spawnRate)
                    {
                        timer++;
                    }
                    else
                    {
                        Enemy enemy = new BossEnemy();
                        enemies.add(enemy);
                        enemyCounter++;
                        resetTimer();
                    }
                }
            }
        }
        if (enemies.size() == enemyNumber)
        {
            setSpawning(false);
            refreshSpawner();
        }
    }

    public List<Tower> getTowers()
    {
        return towers;
    }

    public List<Enemy> getEnemies()
    {
        return enemies;
    }

}
