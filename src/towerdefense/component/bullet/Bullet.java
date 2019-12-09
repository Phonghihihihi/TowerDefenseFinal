package towerdefense.component.bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import towerdefense.component.AbstractEntity;
import towerdefense.component.tower.Tower;
import towerdefense.ui.Game;


public class Bullet extends AbstractEntity {

    private double speed_B;

    public Bullet(String image_Bullet, double posX, double posY, double Speed, double damage) {
        super(posX, posY);
        this.posY = posY;
        this.posX = posX;
        this.speed_B = Speed;

        this.imageV = new ImageView(new Image(image_Bullet));
        imageV.relocate(posX, posY);
        Game.root.getChildren().add(imageV);

    }


    @Override
    public ImageView getImageV() {
        return this.imageV;
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        imageV.relocate(this.getPosX(), this.getPosY());
    }


    public boolean checkEnemyInRange(double x, double y)
    {
        return Math.sqrt(Math.pow(this.getPosX() - x, 2) + Math.pow(this.getPosY() - y, 2)) < 10;
    }


    @Override
    public void update() {

            this.posX += width/100 *speed_B;
            this.posY += height/100 *speed_B;
            imageV.relocate(posX + 7, posY + 7);
    }
}
