package towerdefense.component.enemy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.component.GameConfig;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;



public class BossEnemy extends AbstractEnemy implements Enemy
{
    public BossEnemy() {
        super();
        this.health = GameConfig.BOSS_ENEMY_HEALTH;
        this.armor = GameConfig.BOSS_ENEMY_ARMOR;
        this.speed = GameConfig.BOSS_ENEMY_SPEED;
        this.reward = GameConfig.BOSS_ENEMY_REWARD;
        this.image = new Image("file:src/Assets/Enemy/Abstract Enemy/towerDefense_tile248.png");
        this.imageV = new ImageView(image);
        Game.root.getChildren().add(imageV);
    }

    @Override
    public int getReward() {
        return 0;
    }

    @Override
    public void move() {

    }


    @Override
    public ImageView getImageV() {
        return this.imageV;
    }
}

