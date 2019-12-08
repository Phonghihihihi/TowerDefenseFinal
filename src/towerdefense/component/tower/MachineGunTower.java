package towerdefense.component.tower;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.component.GameConfig;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;

public class MachineGunTower extends AbstractTower {

    public MachineGunTower(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        this.price = GameConfig.MACHINE_GUN_TOWER_PRICE;
        this.range = GameConfig.MACHINE_GUN_TOWER_RANGE;
        this.fireSpeed = GameConfig.MACHINE_GUN_TOWER_FIRESPEED;
        this.damage = GameConfig.MACHINE_GUN_TOWER_DAMAGE;
        this.image = new Image(GameConfig.MACHINE_GUN_TOWER_IMAGE_URL);
        this.imageV = new ImageView(image);
        this.base = new Image("file:src/Assets/Tower/Machine Gun Base.png");
        this.baseV = new ImageView(base);
        Game.root.getChildren().addAll(baseV, imageV);
    }

    @Override
    public ImageView getImageV() {
        return null;
    }


    public void upgrade()
    {
        this.range +=5;
        this.fireSpeed += 5;
        this.damage += 5;
    }
}
