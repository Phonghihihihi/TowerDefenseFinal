package towerdefense.component.enemy;

import javafx.scene.image.Image;
import towerdefense.util.Vector2;

public class NormalEnemy extends AbstractEnemy
{

    public NormalEnemy(Vector2 pos, double i, double i1, Image image1, int i2, int i3, int i4, int i5) {
        super( pos,  i,  i1,  image1,  i2,  i3,  i4,  i5);
    }

    @Override
    public int getReward() {
        return 0;
    }

    @Override
    public void move() {

    }
}

