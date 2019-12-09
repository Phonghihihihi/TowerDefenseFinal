package towerdefense.component.tower;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.component.GameConfig;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;

public class MachineGunTower extends AbstractTower {

    public MachineGunTower(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        this.range = GameConfig.MACHINE_GUN_TOWER_RANGE;
        this.Speed = GameConfig.MACHINE_GUN_TOWER_FIRESPEED;
        this.damage = GameConfig.MACHINE_GUN_TOWER_DAMAGE;
        this.image = new Image(GameConfig.MACHINE_GUN_TOWER_IMAGE_URL);
        this.imageV = new ImageView(image);
        this.base = new Image("file:src/Assets/Tower/Machine Gun Base.png");
        this.image_Bullet = GameConfig.MACHINE_GUN_BULLET;
        this.baseV = new ImageView(base);
        Game.root.getChildren().addAll(baseV, imageV);
    }

    @Override
    public ImageView getImageV() {
        return null;
    }


    @Override
    public double getSpeed() {
        return Speed;
    }

    @Override
    public double getRange() {
        return range;
    }

    @Override
    public double getDamage() {
        return damage;
    }

    public void upgrade()
    {
        this.range +=5;
        this.Speed += 5;
        this.damage += 5;
    }

    @Override
    public String image_Bullet() {
        return image_Bullet;
    }
}
