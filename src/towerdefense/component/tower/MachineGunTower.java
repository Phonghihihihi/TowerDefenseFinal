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
        this.fireSpeed = GameConfig.MACHINE_GUN_TOWER_FIRESPEED;
        this.damage = GameConfig.MACHINE_GUN_TOWER_DAMAGE;
        this.image = new Image(GameConfig.MACHINE_GUN_TOWER_IMAGE_URL);
        this.imageV = new ImageView(image);
        Game.root.getChildren().add(imageV);
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
