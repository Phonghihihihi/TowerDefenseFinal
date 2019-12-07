package towerdefense.component.enemy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.component.GameConfig;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;



public class SmallerEnemy extends AbstractEnemy implements Enemy
{
    public SmallerEnemy(double posX, double posY, double width, double height) {
        super( posX, posY, width, height);
        this.health = GameConfig.SMALLER_ENEMY_HEALTH;
        this.armor = GameConfig.SMALLER_ENEMY_ARMOR;
        this.speed = GameConfig.SMALLER_ENEMY_SPEED;
        this.reward = GameConfig.SMALLER_ENEMY_REWARD;
        this.image = new Image("file:src/Assets/Enemy/Abstract Enemy/towerDefense_tile247.png");
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

