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
    private  boolean delete;


    public Bullet(String bulletURL,double posX , double posY,double width, double health, double speed_B, double damage_B ){
        super(posX, posY);
        this.damage_B = damage_B;
        this.speed_B = speed_B;
        this.is_move = false;
        this.width = width;
        this.height = health;
        this.delete = false;

        this.imageV = new ImageView(new Image(bulletURL));
        Game.root.getChildren().add(imageV);

    }

//    public int getTager() {
//        return tager;
//    }


    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
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


    public boolean checkEnemyInRange(double x, double y)
    {
        return Math.sqrt(Math.pow(this.getPosX() - x, 2) + Math.pow(this.getPosY() - y, 2)) < 20;
    }


    @Override
    public void update() {

            this.posX += width/100 *speed_B;
            this.posY += height/100 *speed_B;
    }
}
