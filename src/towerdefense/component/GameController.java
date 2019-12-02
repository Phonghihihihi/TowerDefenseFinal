package towerdefense.component;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import towerdefense.component.enemy.NormalEnemy;
import towerdefense.ui.TowerDefense;

import java.util.ArrayList;



public class GameController extends AnimationTimer {

    private GraphicsContext graphicsContext;
    private long width;
    private long height;

    public GameField getGameField() {
        return gameField;
    }

    private GameField gameField;
    private GameStage gameStage;

    long frames = 0;
    private long lastFramecountTimeNs = System.nanoTime();
    private long lastTimeNs = System.nanoTime();



    public GameController(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;

        this.width = CommonFunc.GAME_WIDTH;
        this.height = CommonFunc.GAME_HEIGHT;

        this.gameStage = new GameStage(CommonFunc.GAME_WIDTH, CommonFunc.GAME_HEIGHT, new ArrayList<GameEntity>());
        this.gameField = new GameField(gameStage);

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


        //Image image1 = new Image("file:src/Assets/Enemy/Abstract Enemy/towerDefense_tile245.png");
        //NormalEnemy e1 = new NormalEnemy(70,640,1,1,2,3,4,10);
        //TowerDefense.root.getChildren().add(e1.getEnemyV());
        //gameField.getGameEntities().add(e1);
        //for (GameEntity entity: gameField.getGameEntities()){
          //  entity.update();}

        for (GameEntity entity : gameField.getGameEntities()){
            entity.update();
            entity.render(this.graphicsContext);

        }

    }

    public void mouseHandler(MouseEvent mouseEvent){

    }
}
