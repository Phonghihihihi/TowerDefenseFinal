package towerdefense.component.tower;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import towerdefense.component.AbstractEntity;
import towerdefense.component.GameConfig;
import towerdefense.component.GameTile;
import towerdefense.component.enemy.Enemy;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;

public abstract class AbstractTower extends AbstractEntity implements Tower {

    protected Enemy target = null;
    protected int price;
    protected double fireSpeed;
    protected double range;
    protected double damage;
    protected Image base;
    protected ImageView baseV;
    protected Circle circle;
    protected boolean hasCircle = false;

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

    public int getPrice()
    {
        return this.price;
    }

    public int getTileX()
    {
        return (int)(this.posX / GameConfig.TILE_SIZE);
    }

    public int getTileY()
    {
        return (int)(this.posY / GameConfig.TILE_SIZE);
    }

    public Circle getCircle() {
        return circle;
    }

    public double distanceTo(Enemy enemy)
    {
        return Math.sqrt(Math.pow(this.getCenterPosX() - enemy.getPosX(), 2) + Math.pow(this.getCenterPosY() - enemy.getPosY(), 2));
    }

    public boolean checkEnemyInRange(Enemy enemy)
    {
        return this.distanceTo(enemy) <= this.range;
    }

    public double getAngleBetweenEnemy()
    {
        double angle = Math.acos((this.getCenterPosY() - this.target.getPosY()) / distanceTo(this.target)) * GameConfig.PI_TO_DEGREE;
        if (this.getCenterPosX() > this.target.getPosX()) return -angle;
        else return angle;
    }
    @Override
    public void render(GraphicsContext graphicsContext) {
        baseV.relocate(posX + 7,posY + 7);
        imageV.relocate(posX, posY);
    }

    public void drawCircle()
    {
        this.imageV.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Game.root.getChildren().add(circle);
            }
        });
        this.imageV.setOnMouseExited((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Game.root.getChildren().remove(circle);
            }
        }));
    }
    public void update()
    {
        if (distanceTo(target) > this.range)
        {
            this.setTarget(null);
        }
        if (this.target != null) {
            this.imageV.setRotate(getAngleBetweenEnemy());
        }
    }
    public void delete()
    {
        Game.root.getChildren().removeAll(imageV, baseV);
    }
    


}
