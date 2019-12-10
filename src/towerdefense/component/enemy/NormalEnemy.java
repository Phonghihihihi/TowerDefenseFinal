package towerdefense.component.enemy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import towerdefense.component.GameConfig;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;


public class NormalEnemy extends AbstractEnemy implements Enemy
{
    public NormalEnemy() {
        super();
        this.width = GameConfig.NORMAL_ENEMY_WIDTH;
        this.height = GameConfig.NORMAL_ENEMY_HEIGHT;
        this.health = GameConfig.NORMAL_ENEMY_HEALTH;
        this.ENEMY_HEALTH = GameConfig.NORMAL_ENEMY_HEALTH;
        this.armor = GameConfig.NORMAL_ENEMY_ARMOR;
        this.speed = GameConfig.NORMAL_ENEMY_SPEED;
        this.reward = GameConfig.NORMAL_ENEMY_REWARD;
        this.damage = GameConfig.NORMAL_ENEMY_DAMAGE;
        this.image = new Image(GameConfig.NORMAL_ENEMY_IMAGE_URL);
        this.imageV = new ImageView(image);
        Game.root.getChildren().add(imageV);
    }

    @Override
    public Rectangle getHealth_T_Rect() {
        return null;
    }

    @Override
    public Rectangle getHealth_P_Rect() {
        return null;
    }


    @Override
    public ImageView getImageV() {
        return this.imageV;
    }
}

