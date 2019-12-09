package towerdefense.component.tower;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import towerdefense.component.GameConfig;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;

public class MachineGunTower extends AbstractTower {

    public MachineGunTower(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        this.price = GameConfig.MACHINE_GUN_TOWER_PRICE;
        this.range = GameConfig.MACHINE_GUN_TOWER_RANGE;
        this.Speed = GameConfig.MACHINE_GUN_TOWER_FIRESPEED;
        this.damage = GameConfig.MACHINE_GUN_TOWER_DAMAGE;
        this.image = new Image(GameConfig.MACHINE_GUN_TOWER_IMAGE_URL);
        this.imageV = new ImageView(image);
        this.base = new Image("file:src/Assets/Tower/Machine Gun Base.png");
        this.baseV = new ImageView(base);
        this.circle = new Circle(posX + GameConfig.TILE_SIZE/2 + 8, posY + GameConfig.TILE_SIZE/2,range,Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        Game.root.getChildren().addAll(baseV, imageV);
    }

    @Override
    public ImageView getImageV() {
        return null;
    }


    @Override
    public double getSpeed() {
        return 0;
    }

    @Override
    public double getRange() {
        return 0;
    }

    @Override
    public double getDamage() {
        return 0;
    }

    public void upgrade()
    {
        this.range +=5;
        this.Speed += 5;
        this.damage += 5;
    }
}
