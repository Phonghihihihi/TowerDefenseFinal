package towerdefense.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;

import java.util.Map;

public class Reinforcements extends AbstractEntity implements GameEntity {
    private ImageView plane;
    private ImageView shawdowPlane;
    private int timer = 0;
    private int relocateTime = 50;
    private double angle = 45;
    private double speedX = GameConfig.PLANE_SPEED;
    private double speedY = GameConfig.PLANE_SPEED;

    public Reinforcements(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        this.plane = new ImageView(new Image("file:src/Assets/Plane/plane.png"));
        this.shawdowPlane = new ImageView(new Image("file:src/Assets/Plane/shadow.png"));
        Game.root.getChildren().addAll(shawdowPlane, plane);
    }

    @Override
    public ImageView getImageV() {
        return null;
    }

    public double randomAngle(){
        return angle = -90 + Math.random()*180;
    }

    public void move(){
        this.plane.setRotate(angle);
        this.shawdowPlane.setRotate(angle);
        if (timer < relocateTime){
            timer++;
        }
        else {
            this.angle = randomAngle();
            timer = 0;
            speedX = GameConfig.PLANE_SPEED*Math.cos(Math.toRadians(angle));
            speedY = GameConfig.PLANE_SPEED*Math.sin(Math.toRadians(angle))  ;
        }
        if (this.getPosY() > GameConfig.GAME_HEIGHT - GameConfig.TILE_SIZE/2.0 || this.getPosY() < GameConfig.TILE_SIZE/2.0){
            speedY*=(-1);
            angle*=(-1);
        }

        this.setPosX(this.getPosX() + speedX);
        this.setPosY(this.getPosY() + speedY);
    }



    @Override
    public void render(GraphicsContext graphicsContext) {
        plane.relocate(this.getPosX(), this.getPosY());
        shawdowPlane.relocate(this.getPosX() - GameConfig.TILE_SIZE, this.getPosY() + GameConfig.TILE_SIZE);
    }

    @Override
    public void update() {
        this.move();
    }

    public void destroyReinforcements(){
        Game.root.getChildren().removeAll(shawdowPlane, plane);
    }
}
