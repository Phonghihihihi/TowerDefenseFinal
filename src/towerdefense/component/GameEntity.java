package towerdefense.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import towerdefense.util.Vector2;

public interface GameEntity {
    Vector2 getPosition();
    double getWidth();
    double getHeight();
    Image getImage();
    void render(GraphicsContext graphicsContext);
    void update();


}
