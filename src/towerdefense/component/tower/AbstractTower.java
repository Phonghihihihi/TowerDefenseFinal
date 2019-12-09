package towerdefense.component.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.component.AbstractEntity;
import towerdefense.component.GameConfig;
import towerdefense.component.GameTile;
import towerdefense.component.enemy.Enemy;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;

public abstract class AbstractTower extends AbstractEntity implements Tower {

    protected Enemy target = null;
    protected double range;
    protected double damage;
    protected Image base;
    protected ImageView baseV;
    protected double Speed;
    protected int is_Bullet;
    protected String image_Bullet;

    public AbstractTower(double posX, double posY, double width, double height)
    {
        super(posX, posY, width, height);
        is_Bullet = -1;

    }

    @Override
    public double getDamage() {
        return damage;
    }

    @Override
    public double getSpeed() {
        return Speed;
    }

    public int getIs_Bullet() {
        return is_Bullet;
    }

    public void setIs_Bullet(int is_Bullet) {
        this.is_Bullet = is_Bullet;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target)
    {
        this.target = target;
    }

    public int getTileX()
    {
        return (int)(this.posX / GameConfig.TILE_SIZE);
    }

    public int getTileY()
    {
        return (int)(this.posY / GameConfig.TILE_SIZE);
    }

    public double distanceTo(double x, double y)
    {
        return Math.sqrt(Math.pow(this.getPosX() - x, 2) + Math.pow(this.getPosY() - y, 2));
    }

    public boolean checkEnemyInRange(Enemy enemy)
    {
        return this.distanceTo(enemy.getPosX(), enemy.getPosY()) <= this.range;
    }

    public double getAngleBetweenEnemy()
    {
        double angle = Math.acos((this.posY - this.target.getPosY()) / distanceTo(this.target.getPosX(), this.target.getPosY())) * GameConfig.PI_TO_DEGREE;
        if (this.posX > this.target.getPosX()) return -angle;
        else return angle;
    }
    @Override
    public void render(GraphicsContext graphicsContext) {
        baseV.relocate(posX + 7,posY + 7);
        imageV.relocate(posX, posY);
    }
    public void update()
    {
//        if (distanceTo(this.target.getPosX(), this.target.getPosY()) > this.range)
//        {
//            this.setTarget(null);
//        }
//        if (this.target != null) {
            this.imageV.setRotate(getAngleBetweenEnemy());
       // }
    }
    public void delete()
    {
        Game.root.getChildren().removeAll(imageV, baseV);
    }
    


}
