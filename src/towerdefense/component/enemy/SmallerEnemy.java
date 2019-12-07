package towerdefense.component.enemy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.component.GameConfig;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;



public class SmallerEnemy extends AbstractEnemy implements Enemy
{
    public SmallerEnemy() {
        super();
        this.width = GameConfig.SMALLER_ENEMY_WIDTH;
        this.height = GameConfig.SMALLER_ENEMY_HEIGHT;
        this.health = GameConfig.SMALLER_ENEMY_HEALTH;
        this.armor = GameConfig.SMALLER_ENEMY_ARMOR;
        this.speed = GameConfig.SMALLER_ENEMY_SPEED;
        this.reward = GameConfig.SMALLER_ENEMY_REWARD;
        this.image = new Image(GameConfig.SMALLER_ENEMY_IMAGE_URL);
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

