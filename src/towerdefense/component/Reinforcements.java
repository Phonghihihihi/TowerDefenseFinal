package towerdefense.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.ui.TowerDefense;

public class Reinforcements extends AbstractEntity implements GameEntity {
    private ImageView plane;
    private ImageView shawdowPlane;
    private double angle;

    public Reinforcements(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        this.plane = new ImageView(new Image("file:src/Assets/Plane/plane.png"));
        this.shawdowPlane = new ImageView(new Image("file:src/Assets/Plane/shadow.png"));
        TowerDefense.root.getChildren().addAll(shawdowPlane, plane);
    }

    @Override
    public ImageView getImageV() {
        return null;
    }

    public void move(){
        double speedX = GameConfig.PLANE_SPEED;
        double speedY = 0;
        this.setPosX(this.getPosX() + speedX);
        this.setPosY(this.getPosY() + speedY);
    }



    @Override
    public void render(GraphicsContext graphicsContext) {
        plane.relocate(this.getPosX(), this.getPosY());
        shawdowPlane.relocate(this.getPosX() - GameConfig.TILE_SIZE, this.getPosY() - GameConfig.TILE_SIZE);
    }

    @Override
    public void update() {
        this.move();

    }
}
