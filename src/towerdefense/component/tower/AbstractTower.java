package towerdefense.component.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import towerdefense.component.AbstractEntity;
import towerdefense.component.GameConfig;
import towerdefense.component.GameTile;
import towerdefense.component.enemy.Enemy;
import towerdefense.ui.TowerDefense;

public abstract class AbstractTower extends AbstractEntity implements Tower {

    protected Enemy target = null;
    protected double fireSpeed;
    protected double range;
    protected double damage;

    public AbstractTower(double posX, double posY, double width, double height)
    {
        super(posX, posY, width, height);
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
        imageV.relocate(posX, posY);
    }
    public void update()
    {
        if (distanceTo(this.target.getPosX(), this.target.getPosY()) > this.range)
        {
            this.setTarget(null);
        }
        if (this.target != null) {
            this.imageV.setRotate(getAngleBetweenEnemy());
        }
    }
    public void delete()
    {
        TowerDefense.root.getChildren().remove(this.imageV);
    }


}
