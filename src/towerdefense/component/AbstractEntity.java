package towerdefense.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AbstractEntity implements GameEntity {
    private double posX;
    private double posY;
    private double width;
    private double height;
    private Image image;

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getPosX() {
        return posX;
    }

    @Override
    public double getPosY() {
        return posY;
    }

    @Override
    public Image getImage() {
        return image;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    private void setPosX(double posX) {
        this.posX = posX;
    }

    private void setPosY(double posY) {
        this.posY = posY;
    }

    private void setImage(Image image) {
        this.image = image;
    }

    protected AbstractEntity(double posX, double posY, double width, double height, Image image) {
        this.setPosX(posX);
        this.setPosY(posY);
        this.setWidth(width);
        this.setHeight(height);
        this.setImage(image);
    }

    abstract public void render(GraphicsContext graphicsContext);
    abstract public void update();
}
