package towerdefense.component.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import towerdefense.component.AbstractEntity;
import towerdefense.component.GameConfig;
import towerdefense.component.GameTile;
import towerdefense.component.enemy.Enemy;

public abstract class AbstractTower extends AbstractEntity implements Tower {
    protected double fireSpeed;
    protected double range;
    protected double damage;
    public AbstractTower(double posX, double posY, double width, double height)
    {
        super(posX, posY, width, height);
    }

    public int getTileX()
    {
        return (int)(this.posX / GameConfig.TILE_SIZE);
    }

    public int getTileY()
    {
        return (int)(this.posY / GameConfig.TILE_SIZE);
    }

    public double distanceToEnemy(Enemy enemy)
    {
        return Math.sqrt(Math.pow(this.getPosX() - enemy.getPosX(), 2) + Math.pow(this.getPosY() - enemy.getPosY(), 2));
    }

    public boolean checkEnemyInRange(Enemy enemy)
    {
        return this.distanceToEnemy(enemy) <= this.range;
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        imageV.relocate(posX, posY);
    }


}
