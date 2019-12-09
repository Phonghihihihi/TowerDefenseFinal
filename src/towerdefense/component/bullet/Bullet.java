package towerdefense.component.bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.component.AbstractEntity;
import towerdefense.component.enemy.AbstractEnemy;
import towerdefense.component.tower.AbstractTower;
import towerdefense.component.tower.NormalTower;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;


public class Bullet extends AbstractEntity {

    private double damage_B;
    private double speed_B;
    private boolean is_move;

    private  Image bulletIMG ;
    private  ImageView bulletV ;
    public Bullet(String bulletURL,double posX , double posY, double width, double height, double speed_B, double damage_B ){
        super(posX, posY,width,height);
        this.damage_B = damage_B;
        this.speed_B = speed_B;
        this.is_move = false;

        this.imageV = new ImageView(new Image(bulletURL));
        Game.root.getChildren().add(imageV);

    }

    @Override
    public ImageView getImageV() {
        return this.imageV;
    }

    public boolean isIs_move() {
        return is_move;
    }

    public void setIs_move(boolean is_move) {
        this.is_move = is_move;
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        imageV.relocate(this.getPosX(), this.getPosY());
    }


    public double distanceTo(double x, double y)
    {
        return Math.sqrt(Math.pow(this.getPosX() - x, 2) + Math.pow(this.getPosY() - y, 2));
    }

    public void setBullet( double posX, double posY, double wigth, double height){
        this.posX = posX;
        this.posY = posY;
        this.width = wigth;
        this.height = height;
        is_move = true;


    }


    @Override
    public void update() {

            this.posX += width/100 *speed_B;
            this.posY += height/100 *speed_B;
    }
}
