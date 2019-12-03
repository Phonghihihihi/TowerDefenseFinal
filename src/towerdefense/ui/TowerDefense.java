package towerdefense.ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import towerdefense.component.*;
import towerdefense.component.enemy.NormalEnemy;

public class TowerDefense extends Application {


    private double canvasMouseY;
    private double canvasMouseX;
    public static Group root = new Group();

    @Override
    public void start(Stage stage) throws Exception {
        // Set up stage and main BorderPane

        Canvas canvas = new Canvas(GameConfig.CANVAS_WIDTH, GameConfig.CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //GameController gameController = new GameController(gc);

        //canvas.setOnMouseClicked(gameController::mouseHandler);

        //Group root = new Group();
        root.getChildren().add(canvas);
        Scene theScene = new Scene(root);

        stage.setTitle(GameConfig.GAME_NAME);
        stage.setScene(theScene);

        GameStage gameStage = new GameStage();
        GameField gameField = new GameField();

        Button next_wave = new Button("Next Wave");
        next_wave.relocate(1300,400);
        next_wave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        root.getChildren().add(next_wave);
        TileMap.drawMap(gc);
        //NormalEnemy e1 = new NormalEnemy(64,640,1,10,2,3,4,5);
        //gameField.getGameEntities().add(e1);
        //gameField.spawnEnemies();

        new AnimationTimer()
        {
            public void handle(long currentTimeNs)
            {
                if(!gameStage.isWaveOver())
                {
                    gameField.spawnEnemies();
                }
                for (int i=0; i<gameField.getGameEntities().size(); i++)
                {
                    gameField.getGameEntities().get(i).update();
                    gameField.getGameEntities().get(i).render(gc);
                    if (gameField.getGameEntities().get(i).getPosX()  == 1220)
                    {
                        gameField.getGameEntities().remove(i);

                    }
                }
                if (gameField.getGameEntities().isEmpty())
                {
                   // gameStage.setWaveOver(true);
                    //gameField.setSpawning(false);
                }
            }
        }.start();
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
