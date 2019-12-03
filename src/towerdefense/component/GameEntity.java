package towerdefense.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public interface GameEntity {
    double getPosX();
    double getPosY();
    void setPosX(double posX);
    void setPosY(double posY);
    double getWidth();
    double getHeight();
    ImageView getImageV();
    void render(GraphicsContext graphicsContext);
    void update();


}
