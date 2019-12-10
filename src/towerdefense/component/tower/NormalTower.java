package towerdefense.component.tower;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import towerdefense.component.GameConfig;
import towerdefense.component.bullet.Bullet;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;

public class NormalTower extends AbstractTower {
    public NormalTower (double posX, double posY, double width, double height)
    {
        super(posX, posY, width, height);
        this.price = GameConfig.NORMAL_TOWER_PRICE;
        this.range = GameConfig.NORMAL_TOWER_RANGE;
        this.Speed = GameConfig.NORMAL_TOWER_FIRESPEED;
        this.damage = GameConfig.NORMAL_TOWER_DAMAGE;
        this.image = new Image(GameConfig.NORMAL_TOWER_IMAGE_URL);
        this.imageV = new ImageView(image);
        this.image_Bullet = GameConfig.NORMAL_BULLET;
        this.base = new Image("file:src/Assets/Tower/Normal Base.png");
        this.baseV = new ImageView(base);
        bullet = new Bullet(image_Bullet, posX , posY , Speed, damage);
        this.circle = new Circle(posX + GameConfig.TILE_SIZE/2 + 8, posY + GameConfig.TILE_SIZE/2,range,Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        Game.root.getChildren().addAll(baseV, imageV);

    }

    @Override
    public ImageView getImageV() {
        return imageV;
    }

    @Override
    public double getSpeed() {
        return Speed;
    }

    @Override
    public double getRange() {
        return this.range;
    }

    @Override
    public double getDamage() {
        return damage;
    }


    @Override
    public String image_Bullet() {
        return image_Bullet;
    }


}
