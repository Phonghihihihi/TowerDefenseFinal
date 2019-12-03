package towerdefense.component.enemy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.ui.TowerDefense;


public class NormalEnemy extends AbstractEnemy implements Enemy
{
    public NormalEnemy(double posX, double posY, double width, double height, int health, int armor, int reward, int speed) {
        super( posX, posY, width, height, health, armor, reward, speed);
        this.image = new Image("file:src/Assets/Enemy/Abstract Enemy/towerDefense_tile245.png");
        this.enemyV = new ImageView(image);
        TowerDefense.root.getChildren().add(enemyV);

    }

    @Override
    public int getReward() {
        return 0;
    }

    @Override
    public void move() {

    }



}

