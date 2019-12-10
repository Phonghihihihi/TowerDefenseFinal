package towerdefense.component.enemy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import towerdefense.component.GameConfig;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;



public class TankerEnemy extends AbstractEnemy implements Enemy
{
    public TankerEnemy() {
        super();
        this.width = GameConfig.TANKER_ENEMY_WIDTH;
        this.height = GameConfig.TANKER_ENEMY_HEIGHT;
        this.health = GameConfig.TANKER_ENEMY_HEALTH;
        this.armor = GameConfig.TANKER_ENEMY_ARMOR;
        this.speed = GameConfig.TANKER_ENEMY_SPEED;
        this.reward = GameConfig.TANKER_ENEMY_REWARD;
        this.ENEMY_HEALTH = GameConfig.TANKER_ENEMY_HEALTH;
        this.image = new Image(GameConfig.TANKER_ENEMY_URL);
        this.imageV = new ImageView(image);
        Game.root.getChildren().add(imageV);
    }

    @Override
    public int getReward() {
        return 0;
    }

    @Override
    public void setHealth() {
        this.health -= 1;
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

