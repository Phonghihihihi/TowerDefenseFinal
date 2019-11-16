package towerdefense.component;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import towerdefense.component.enemy.NormalEnemy;
import towerdefense.util.Vector2;

import java.util.ArrayList;



public class GameController extends AnimationTimer {

    private GraphicsContext graphicsContext;
    private long width;
    private long height;
    private GameField gameField;
    private GameStage gameStage;
    private Map map;

    long frames = 0;
    private long lastFramecountTimeNs = System.nanoTime();
    private long lastTimeNs = System.nanoTime();



    public GameController(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;

        this.width = CommonFunc.GAME_WIDTH;
        this.height = CommonFunc.GAME_HEIGHT;

        this.gameStage = new GameStage(CommonFunc.GAME_WIDTH, CommonFunc.GAME_HEIGHT, new ArrayList<GameEntity>());
        this.gameField = new GameField(gameStage);
        this.map = new Map();
    }

    public void update(){
        for (GameEntity entity: gameField.getGameEntities()){
            entity.update();
        }
    }

    @Override
    public void handle(long currentTimeNs) {
        double deltaTime = (currentTimeNs - lastTimeNs)/ CommonFunc.NS_IN_SEC;
        lastTimeNs = currentTimeNs;

        long FPS_INTERVAL_NS = (long) (2 * CommonFunc.NS_IN_SEC);
        if (currentTimeNs - lastFramecountTimeNs > FPS_INTERVAL_NS){
            System.out.println("Average framerate "
                    + frames / (FPS_INTERVAL_NS / CommonFunc.NS_IN_SEC));
            frames = 0;
            lastFramecountTimeNs = currentTimeNs;
        }

        map.drawMap(graphicsContext);
        Vector2 pos = new Vector2(128, 768);
        Image image1 = new Image("file:src/Assets/Enemy/Abstract Enemy/towerDefense_tile245.png");
        gameField.getGameEntities().add(new NormalEnemy(pos,1,1,image1,2,3,4,10));

        for (GameEntity entity : gameField.getGameEntities()){
            entity.render(this.graphicsContext);
        }
    }

    public void mouseHandler(MouseEvent mouseEvent){

    }
}
