package towerdefense.component;

import towerdefense.component.bullet.Bullet;
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
    private int normalCounter = 0;
    private int smallerCounter = 0;
    private int tankerCounter = 0;
    private int bossCounter = 0;
    private long timer = 0;
    private long spawnRate = GameConfig.SPAWN_RATE;
    private int timer1 = 0;
    private int timer2 = 0;
    private int timer3 = 0;
    private int timer4 = 0;
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
    List<Bullet> bullets = new ArrayList<Bullet>();

    public GameField(){}

    public int getWaveCount() {
        return waveCount;
    }

    public void setWaveCount() {
        this.waveCount++;
        //System.out.println(waveCount);
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
        normalCounter = 0;
        smallerCounter = 0;
        tankerCounter = 0;
        bossCounter = 0;
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
        if (waveCount > 1)
        {
            normalNumber++;
        }
        if (waveCount % 2 == 0)
        {
            smallerNumber = GameConfig.SMALLER_ENEMY_WAVE_NUMBER + waveCount/2 - 1;
        }
        else
        {
            smallerNumber = 0;
        }

        if (waveCount % 5 == 0)
        {
            tankerNumber = GameConfig.TANKER_ENEMY_WAVE_NUMBER + waveCount/5 - 1;
        }
        else
        {
            tankerNumber = 0;
        }

        if (waveCount % 10 == 0)
        {
            bossNumber = GameConfig.BOSS_ENEMY_WAVE_NUMBER + waveCount/10 - 1;
        }
        else
        {
            bossNumber = 0;
        }
        enemyNumber = normalNumber + smallerNumber + tankerNumber + bossNumber;
    }
    /*public void spawnEnemies()
    {
        if (enemyCounter < enemyNumber && isSpawning)
        {
            for (int i=0; i < normalNumber;i++){
                if (timer1 < spawnRate) {
                    timer1++;
                } else {
                    //Enemy enemy = new NormalEnemy();
                    enemies.add(new NormalEnemy());
                    enemyCounter++;
                    timer1 = 0;
                }
            }
            for (int i=0; i < smallerNumber; i++)
            {
                if (timer2 < spawnRate)
                {
                    timer2++;
                }
                else
                {
                    Enemy enemy = new SmallerEnemy();
                    enemies.add(enemy);
                    enemyCounter++;
                    timer2 = 0;
                }
            }
            for (int i=0; i < tankerNumber; i++)
            {
                if (timer3 < spawnRate)
                {
                    timer3++;
                }
                else
                {
                    Enemy enemy = new TankerEnemy();
                    enemies.add(enemy);
                    enemyCounter++;
                    timer3 = 0;
                }
            }
            for (int i=0; i < bossNumber; i++)
            {
                if (timer4 < spawnRate)
                {
                    timer4++;
                }
                else
                {
                    Enemy enemy = new BossEnemy();
                    enemies.add(enemy);
                    enemyCounter++;
                    timer4 = 0;

                }
            }
        }
        if (enemies.size() == enemyNumber)
        {
            setSpawning(false);
            refreshSpawner();
        }
    }*/
    public void spawnEnemies()
    {
        if (isSpawning && enemyCounter < enemyNumber)
        {
            timer++;
            if(timer % spawnRate == 0)
            {
                if (normalCounter < normalNumber)
                {
                    enemies.add(new NormalEnemy());
                    enemies.get(enemies.size()-1).setENEMY_HEALTH();
                    normalCounter++;
                    enemyCounter++;
                }

                if (smallerCounter < smallerNumber)
                {
                    enemies.add(new SmallerEnemy());
                    enemies.get(enemies.size()-1).setENEMY_HEALTH();
                    smallerCounter++;
                    enemyCounter++;
                }

                if (tankerCounter < tankerNumber && normalCounter == normalNumber)
                {
                    enemies.add(new TankerEnemy());
                    enemies.get(enemies.size()-1).setENEMY_HEALTH();
                    tankerCounter++;
                    enemyCounter++;
                }

                if (bossCounter < bossNumber && normalCounter == normalNumber && tankerCounter == tankerNumber)
                {
                    enemies.add(new BossEnemy());
                    enemies.get(enemies.size()-1).setENEMY_HEALTH();
                    bossCounter++;
                    enemyCounter++;
                }
            }
        }
        if (enemyCounter == enemyNumber)
        {
            setSpawning(false);
            refreshSpawner();
            resetTimer();
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

    public List<Bullet> getBullets() {
        return bullets;
    }

    public int getTankerNumber() {
        return tankerNumber;
    }
}