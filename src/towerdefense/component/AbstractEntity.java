package towerdefense.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import towerdefense.util.Vector2;

public abstract class AbstractEntity implements GameEntity {
    private Vector2 position;
    private double width;
    private double height;
    protected Image image;

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
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

    private void setImage(Image image) {
        this.image = image;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    protected AbstractEntity(Vector2 position, double width, double height, Image image) {
        this.position = position;
        this.setWidth(width);
        this.setHeight(height);
        this.setImage(image);
    }

    abstract public void render(GraphicsContext graphicsContext);
    abstract public void update();
}
