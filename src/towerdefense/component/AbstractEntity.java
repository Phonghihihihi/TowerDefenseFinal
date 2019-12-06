package towerdefense.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        return this.getPosX() + this.width/2;
    }

    public double getCenterPosY()
    {
        return this.getPosY() + this.height/2;
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

    abstract public void render(GraphicsContext graphicsContext);

}
