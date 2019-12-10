package towerdefense.component.enemy;

import javafx.scene.shape.Rectangle;
import towerdefense.component.GameEntity;


public interface Enemy extends GameEntity {
    int getReward();

    void takeDamage(int damage);

    void move(int path[][]);

    void update();

    void destroyEnemy();

    void attack();

    void death();

    boolean isDestroyed();

    public void remove_Health();


    void setHealth();
    double getHealth();
    public Rectangle getHealth_T_Rect();

    public Rectangle getHealth_P_Rect();

    public void setENEMY_HEALTH();
}
