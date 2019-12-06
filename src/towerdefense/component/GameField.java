package towerdefense.component;

import towerdefense.component.enemy.Enemy;
import towerdefense.component.enemy.NormalEnemy;
import towerdefense.component.tower.Tower;

import java.util.ArrayList;
import java.util.List;

public class GameField {
    private int normalNumber = GameConfig.NORMAL_ENEMY_WAVE_NUMBER;
    private int enemyCounter = 0;
    private int spawnRate = GameConfig.SPAWN_RATE;
    private int timer = 0;
    private boolean isSpawning = false;
    private boolean placingNormalTower = false;
    private boolean placingMachinGunTower = false;
    private boolean upgradingTower = false;
    private boolean sellingTower = false;
    //List<GameEntity> gameEntities = new ArrayList<GameEntity>(GameConfig.MAP_TILE);
    Reinforcements reinforcements = new Reinforcements(0, 400, 64, 64);
    List<Enemy> enemies = new ArrayList<Enemy>();
    List<Tower> towers = new ArrayList<Tower>();

    public GameField(){}

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

    public boolean isPlacingMachinGunTower()
    {
        return placingMachinGunTower;
    }

    public void setPlacingMachinGunTower(boolean placingMachinGunTower)
    {
        this.placingMachinGunTower = placingMachinGunTower;
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

    public Reinforcements getReinforcements() {
        return reinforcements;
    }

    public void spawnEnemies()
    {
        if (this.enemyCounter < normalNumber && isSpawning)
        {
            if (timer < spawnRate)
            {
                timer ++;
            }
            else
            {
                NormalEnemy e1 = new NormalEnemy(65, 640, 64, 64);
                enemies.add(e1);
                this.enemyCounter++;
                timer = 0;

            }
        }
        else if (enemyCounter >= normalNumber)
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
