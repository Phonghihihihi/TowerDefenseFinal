package towerdefense.component.enemy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.component.GameConfig;
import towerdefense.ui.TowerDefense;


public class NormalEnemy extends AbstractEnemy implements Enemy
{
    public NormalEnemy(double posX, double posY, double width, double height) {
        super( posX, posY, width, height);
        this.health = GameConfig.NORMAL_ENEMY_HEALTH;
        this.armor = GameConfig.NORMAL_ENEMY_ARMOR;
        this.speed = GameConfig.NORMAL_ENEMY_SPEED;
        this.reward = GameConfig.NORMAL_ENEMY_REWARD;
        this.image = new Image(GameConfig.NORMAL_ENEMY_IMAGE_URL);
        this.imageV = new ImageView(image);
        TowerDefense.root.getChildren().add(imageV);
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
        return this.enemyV;
    }
}

