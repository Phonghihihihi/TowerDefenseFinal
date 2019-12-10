package towerdefense.component.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import towerdefense.component.AbstractEntity;
import towerdefense.component.GameConfig;
import towerdefense.component.TileMap;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;

import java.io.File;
import java.util.Map;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public abstract class AbstractEnemy extends AbstractEntity implements Enemy {


    protected int health;
    protected int armor;
    protected int reward;
    protected int speed;
    protected int ENEMY_HEALTH;

    protected MediaPlayer attack = new MediaPlayer(new Media(new File("src/Assets/Music/Attack.mp3").toURI().toString()));
    protected MediaPlayer death = new MediaPlayer(new Media(new File("src/Assets/Music/Death.mp3").toURI().toString()));

    public Rectangle Health_T_Rect;
    public Rectangle Health_P_Rect;

    private double speedX = 0;
    private double speedY = -this.getSpeed();
    private boolean destroyed = false;

    public AbstractEnemy() {
        super(GameConfig.SPAWN_X, GameConfig.SPAWN_Y);
    }

    public int getSpeed() {
        return speed;
    }

    public int getArmor() {
        return armor;
    }

    public double getHealth() {
        return health;
    }

    @Override
    public void takeDamage(int damage) {
        health -= Math.max((damage - armor), 0);
        if (health<=0){
            destroyed = true;
            delete();
        }
    }


    public void setENEMY_HEALTH(){
        Health_T_Rect = new Rectangle(posX - 60, posY - 30, health *60/ENEMY_HEALTH , 5);
        // Health_T_Rect.setStroke(Color.BLACK);
        Health_T_Rect.setStrokeWidth(2);
        Health_T_Rect.setFill(Color.RED);

        Health_P_Rect = new Rectangle(posX + health - 60, posY - 30, (ENEMY_HEALTH - health)*60/ENEMY_HEALTH, 5);
        Health_P_Rect.setStrokeType(StrokeType.OUTSIDE);
        Health_P_Rect.setFill(Color.LIMEGREEN);
        Game.root.getChildren().addAll(Health_P_Rect,Health_T_Rect);
    }
    public void remove_Health(){
        Game.root.getChildren().removeAll(Health_P_Rect,Health_T_Rect);
    }
    public Rectangle getHealth_T_Rect() {
        return Health_T_Rect;
    }

    public Rectangle getHealth_P_Rect() {
        return Health_P_Rect;
    }
    public void attack()
    {
        attack.play();
    }

    @Override
    public void death() {
        death.play();
    }

    public void destroyEnemy() {
        Game.root.getChildren().remove(imageV);
    }

    private double distanceToWayPoint(int tile_X, int tile_Y){
        return Math.sqrt((tile_X-this.getPosX())*(tile_X-this.getPosX()) + (tile_Y-this.getPosY())*(tile_Y-this.getPosY()));
    }


    public void move (int[][] path) {

        int tile_Y = (int) (this.getPosX() / GameConfig.TILE_SIZE) ;
        int tile_X = (int) (this.getPosY() / GameConfig.TILE_SIZE) ;
        if (this.getCenterPosX() < (GameConfig.GAME_WIDTH)) {
            if (path[tile_X][tile_Y] == 8 && distanceToWayPoint(tile_Y*64 + 32, tile_X*64 + 32) < 4) {
                speedY = -this.getSpeed();
                speedX = 0;
                imageV.setRotate(-90);
            } else if (path[tile_X][tile_Y] == 2 && distanceToWayPoint(tile_Y*64 + 32, tile_X*64 + 32) < 4) {
                speedY = this.getSpeed();
                speedX = 0;
                imageV.setRotate(90);
            } else if (path[tile_X][tile_Y] == 4 && distanceToWayPoint(tile_Y*64 + 32, tile_X*64 +32) < 4) {
                speedX = -this.getSpeed();
                speedY = 0;
                imageV.setRotate(-180);
            } else if (path[tile_X][tile_Y] == 6 && distanceToWayPoint(tile_Y*64 + 32, tile_X*64 + 32) < 4) {
                speedX = this.getSpeed();
                speedY = 0;
                imageV.setRotate(0);
            }
        }
        this.setPosX(this.getPosX() + speedX);
        this.setPosY(this.getPosY() + speedY);

    }
    public void update() {
        this.move(TileMap.MAP_PATH);
        Health_P_Rect.setWidth((ENEMY_HEALTH - health)*60/ENEMY_HEALTH);
        Health_T_Rect.setWidth(Math.max(health *60 / ENEMY_HEALTH,0));
        if (health <= 0)
        {
            Game.root.getChildren().removeAll(imageV, Health_P_Rect, Health_T_Rect);
        }
        }

    public void render(GraphicsContext graphicsContext)
    {
        Health_T_Rect.relocate(posX  -32, posY - 37);
        Health_P_Rect.relocate(posX + health*60/ENEMY_HEALTH  -32 , posY -37);
        imageV.relocate(this.getPosX() - GameConfig.TILE_SIZE/2.0, this.getPosY() - GameConfig.TILE_SIZE/2.0);

        if (this.getCenterPosX() > (GameConfig.GAME_WIDTH))
        {
            Game.root.getChildren().remove(Health_T_Rect);
            Game.root.getChildren().remove(Health_P_Rect);
            Game.root.getChildren().remove(imageV);
        }
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    public void delete()
    {
        Game.root.getChildren().removeAll(Health_T_Rect, Health_P_Rect);
        super.delete();


    }
}
