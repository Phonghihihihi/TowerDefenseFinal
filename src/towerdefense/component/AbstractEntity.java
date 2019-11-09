package towerdefense.component;

public abstract class AbstractEntity implements GameEntity {
    private double posX;
    private double posY;
    private double width;
    private double height;

    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    public double getPosX() {
        return posX;
    }
    public double getPosY() {
        return posY;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setPosX(double posX) {
        this.posX = posX;
    }
    public void setPosY(double posY) {
        this.posY = posY;
    }
    protected AbstractEntity(double posX, double posY, double width, double height){
        this.setPosX(posX);
        this.setPosY(posY);
        this.setWidth(width);
        this.setHeight(height);
    }

}
