package towerdefense.component.bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.component.AbstractEntity;
import towerdefense.component.enemy.AbstractEnemy;
import towerdefense.component.tower.AbstractTower;
import towerdefense.component.tower.NormalTower;
import towerdefense.ui.TowerDefense;


public class Bullet extends AbstractEntity {

    private double damage_B;
    private double range_B;
    private double speed_B;
    private double direction_B;

    private  Image bulletIMG ;
    private  ImageView bulletV ;
    public Bullet(String bulletURL,double posX , double posY, double width, double height, double speed_B, double damage_B, double range_B){
        super(posX, posY,width,height);
        this.damage_B = damage_B;
        this.range_B = range_B;
        this.speed_B = speed_B;
        this.direction_B = direction_B;
        this.imageV = new ImageView(new Image(bulletURL));
        TowerDefense.root.getChildren().add(imageV);
    }

    @Override
    public ImageView getImageV() {
        return this.imageV;
    }


    @Override
    public void render(GraphicsContext graphicsContext) {
        imageV.relocate(this.getPosX(), this.getPosY());
    }

    @Override
    public void update() {
        this.posX = direction_B* speed_B;
        this.posY = direction_B* speed_B;

    }
}
