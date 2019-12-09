package towerdefense.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;

public abstract class AbstractEntity implements GameEntity {
    protected double posX;
    protected double posY;
    protected double width;
    protected double height;
    protected Image image;
    protected ImageView imageV;

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }


    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }



    @Override
    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getCenterPosX()
    {
        return this.getPosX() + 32;
    }

    public double getCenterPosY()
    {
        return this.getPosY() + 32;
    }

    @Override
    public void setPosX(double posX) {
        this.posX = posX;
    }

    @Override
    public void setPosY(double posY) {
        this.posY = posY;
    }

    protected AbstractEntity(double posX, double posY, double width, double height) {
        this.setPosX(posX);
        this.setPosY(posY);
        this.setWidth(width);
        this.setHeight(height);

    }
    protected AbstractEntity(double posX, double posY)
    {
        this.setPosX(posX);
        this.setPosY(posY);
    }

    public void delete()
    {
        Game.root.getChildren().remove(this.imageV);
    }


}
