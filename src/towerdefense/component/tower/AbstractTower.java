//package towerdefense.component.tower;
//
//import javafx.scene.control.Button;
//import towerdefense.component.GameTile;
//import towerdefense.component.enemy.Enemy;
//
//public abstract class AbstractTower implements GameTile {
//    protected double posX;
//    protected double posY;
//    protected double fireSpeed;
//    protected double range;
//    protected double damage;
//    protected Button buyTower;
//    public AbstractTower(double posX, double posY)
//    {
//        this.setPosX(posX);
//        this.setPosY(posY);
//    }
//
//    @Override
//    public double getPosX() {
//        return posX;
//    }
//
//    @Override
//    public void setPosX(double posX) {
//        this.posX = posX;
//    }
//    public double distanceToEnemy(Enemy enemy)
//    {
//        return (Math.sqrt (Math.pow(this.getPosX() - enemy.getPosX()), 2) + Math.pow(this.getPosY() - enemy.getPosY(), 2));
//    }
//
//    public boolean checkEnemyInRange()
//    {
//        return this.distanceToEnemy() <= this.range;
//    }
//
//    @Override
//    public double getPosY() {
//        return posY;
//    }
//
//    @Override
//    public void setPosY(double posY) {
//        this.posY = posY;
//    }
//
//
//
//
//
//}
