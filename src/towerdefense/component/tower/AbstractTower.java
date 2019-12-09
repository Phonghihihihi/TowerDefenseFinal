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
import towerdefense.component.bullet.Bullet;
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

    protected double Speed;
    protected int is_Bullet;
    protected String image_Bullet;
    protected Bullet bullet;

    public AbstractTower(double posX, double posY, double width, double height)
    {
        super(posX, posY, width, height);

    }

    public Bullet getBullet() {
        return bullet;
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
    public void bullet_update(){
        bullet.update();
        if(this.bullet.checkEnemyInRange(target.getPosX() - 32, target.getPosY()- 32)){
            bullet.setPosX(posX);
            bullet.setPosY(posY);
            target.setHealth();

            if (target.getHealth() <= 0) {
                bullet.setImageV(false);
                target.delete();
                //System.out.println(1);
                target = null;
            }
        }
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
                circle.setRadius(range);
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
        if (distanceTo(target) >= this.range)
        {
            this.setTarget(null);
        }
        if (this.target != null) {
            bullet.setImageV(true);
            this.imageV.setRotate(getAngleBetweenEnemy());
            this.bullet.setWidth(target.getPosX() - 32- posX);
            this.bullet.setHeight(target.getPosY() - 32 - posY );
            this.bullet_update();
        }
        else{
            System.out.println(1);
            bullet.setImageV(false);
        }
    }
    public void delete()
    {
        Game.root.getChildren().removeAll(imageV, baseV);
    }
    


}
