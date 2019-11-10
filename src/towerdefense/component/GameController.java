package towerdefense.component;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;



public class GameController extends AnimationTimer {

    private GraphicsContext graphicsContext;
    private long width;
    private long height;
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

    public String[][] MAP_SPRITES = new String[][] {
            {"024","024","024","024","069","070","070","070","070","070","071","024","024","024","024","024","024","024","024","024",},
            {"024","024","024","024","092","093","093","093","093","093","094","024","024","024","024","024","024","024","024","024",},
            {"024","024","024","024","092","093","072","116","073","093","094","024","024","024","024","024","024","024","024","024",},
            {"024","024","024","024","092","093","094","024","092","093","094","024","024","024","024","024","024","024","024","024",},
            {"024","024","024","024","092","093","094","024","092","093","094","024","024","024","024","024","024","024","024","024",},
            {"069","070","070","070","096","093","094","024","092","093","094","024","024","024","024","024","024","024","024","024",},
            {"092","093","093","093","093","093","094","024","092","093","094","024","024","024","024","024","024","024","024","024",},
            {"092","093","072","116","116","116","117","024","092","093","094","024","024","024","024","024","024","024","024","024",},
            {"092","093","094","024","024","024","024","024","092","093","094","024","024","024","024","024","024","024","024","024",},
            {"092","093","094","024","024","024","024","024","092","093","095","070","070","070","070","070","070","070","070","070",},
            {"092","093","094","024","024","024","024","024","092","093","093","093","093","093","093","093","093","093","093","093",},
            {"092","093","094","024","024","024","024","024","115","116","116","116","116","116","116","116","116","116","116","116",},




    };

    private void drawMap(){
        for (int i = 0; i < MAP_SPRITES.length; i++) {
            for (int j = 0; j < MAP_SPRITES[i].length; j++) {
                graphicsContext.drawImage(new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile"
                        + MAP_SPRITES[i][j]  +".png" ), j * CommonFunc.TILE_SIZE, i * CommonFunc.TILE_SIZE);
            }
        }
    }

    public void render(){
        drawMap();

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

        drawMap();
        for (GameEntity entity : gameField.getGameEntities()){
            entity.render(this.graphicsContext);
        }
    }

    public void mouseHandler(MouseEvent mouseEvent){

    }
}
