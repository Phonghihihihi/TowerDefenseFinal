package towerdefense.component.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.component.GameConfig;
import towerdefense.ui.TowerDefense;

public class NormalTower extends AbstractTower {
    public NormalTower (double posX, double posY, double width, double height)
    {
        super(posX, posY, width, height);
        this.range = GameConfig.NORMAL_TOWER_RANGE;
        this.fireSpeed = GameConfig.NORMAL_TOWER_FIRESPEED;
        this.damage = GameConfig.NORMAL_TOWER_DAMAGE;
        this.image = new Image(GameConfig.NORMAL_TOWER_IMAGE_URL);
        this.imageV = new ImageView(image);
        TowerDefense.root.getChildren().add(imageV);
    }

    @Override
    public ImageView getImageV() {
        return null;
    }


    @Override
    public void update() {

    }

    public void upgrade()
    {
        this.damage += 5;
        this.range += 5;
        this.fireSpeed +=5;
    }
}
