package towerdefense.ui;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import towerdefense.component.*;
import towerdefense.component.enemy.Enemy;
import towerdefense.component.tower.MachineGunTower;
import towerdefense.component.tower.NormalTower;
import towerdefense.component.tower.Tower;

import java.io.IOException;

public class Game {
    public static Group root = new Group();
    private Stage stage = new Stage();
    private AnimationTimer timer;
    public Stage getStage() {
        return stage;
    }
    private GameStage gameStage;
    private GameField gameField;
    boolean isResetGame = false;

    public void startGame(){
        timer.start();
        stage.show();
    }

    public Game(){
        Canvas canvas = new Canvas(GameConfig.CANVAS_WIDTH, GameConfig.CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Scene theScene = new Scene(root);

        stage.setTitle(GameConfig.GAME_NAME);
        stage.setScene(theScene);
        TileMap.drawUI(gc);
        TileMap.drawMap(gc);
        gameStage = new GameStage();
        gameField = new GameField();

        Button next_wave = new Button("Next Wave");
        next_wave.relocate(1200,400);

        ImageView normal_preplace = new ImageView(new Image("file:src/Assets/Tower/Normal_preplace.png"));
        ImageView machine_gun_preplace = new ImageView(new Image("file:src/Assets/Tower/250_preplace.png"));

        Button buy_normal_tower = new Button ("", new ImageView(new Image(GameConfig.NORMAL_TOWER_IMAGE_URL)));
        buy_normal_tower.relocate(1200,250);
        buy_normal_tower.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameField.setPlacingNormalTower(true);
                gameField.setPlacingMachineGunTower(false);
                if (!root.getChildren().contains(normal_preplace)) {
                    root.getChildren().add(normal_preplace);
                }
                root.getChildren().remove(machine_gun_preplace);
            }
        });

        Button buy_machine_gun_tower = new Button ("", new ImageView(new Image(GameConfig.MACHINE_GUN_TOWER_IMAGE_URL)));
        buy_machine_gun_tower.relocate(1200,150);
        buy_machine_gun_tower.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameField.setPlacingMachineGunTower(true);
                gameField.setPlacingNormalTower(false);
                if(!root.getChildren().contains(machine_gun_preplace)) {
                    root.getChildren().add(machine_gun_preplace);
                }
                root.getChildren().remove(normal_preplace);
            }
        });


        Button upgrade = new Button ("Upgrade");
        upgrade.relocate(1200,350);
        upgrade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameField.isUpgradingTower())
                {
                    gameField.setUpgradingTower(true);
                }
                gameField.setSellingTower(false);
            }
        });

        Button sell = new Button ("Sell");
        sell.relocate(1200,450);
        sell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameField.setSellingTower(true);
            }
        });


        theScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getX() <= GameConfig.GAME_WIDTH) {

                    int tileX = (int) (mouseEvent.getX() / GameConfig.TILE_SIZE);
                    int tileY = (int) (mouseEvent.getY() / GameConfig.TILE_SIZE);
                    int mouseX = tileX * GameConfig.TILE_SIZE;
                    int mouseY = tileY * GameConfig.TILE_SIZE;

                    if (TileMap.MAP_PATH[tileY][tileX] == 0) {
                        if (gameField.isPlacingNormalTower()) {
                            NormalTower t1 = new NormalTower(mouseX, mouseY, 50, 50);
                            t1.render(gc);
                            gameField.getTowers().add(t1);
                            gameField.setPlacingNormalTower(false);
                            TileMap.MAP_PATH[tileY][tileX] = 1;
                            root.getChildren().remove(normal_preplace);
                        } else if (gameField.isPlacingMachineGunTower()) {
                            MachineGunTower t2 = new MachineGunTower(mouseX, mouseY, 50, 50);
                            t2.render(gc);
                            gameField.getTowers().add(t2);
                            gameField.setPlacingMachineGunTower(false);
                            TileMap.MAP_PATH[tileY][tileX] = 1;
                            root.getChildren().remove(machine_gun_preplace);
                        }
                    } else if (TileMap.MAP_PATH[tileY][tileX] == 1) {
                        for (int i = 0; i < gameField.getTowers().size(); i++) {
                            if (gameField.getTowers().get(i).getPosX() == mouseX && gameField.getTowers().get(i).getPosY() == mouseY) {
                                if (gameField.isSellingTower()) {
                                    gameField.getTowers().get(i).delete();
                                    gameField.getTowers().remove(i);
                                    TileMap.MAP_PATH[tileY][tileX] = 0;
                                    gameField.setSellingTower(false);
                                } else if (gameField.isUpgradingTower()) {
                                    gameField.getTowers().get(i).upgrade();
                                }
                            }
                        }
                    }
                }
            }
        });

        theScene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getX() < GameConfig.GAME_WIDTH) {
                    int tileX = (int) (mouseEvent.getX() / GameConfig.TILE_SIZE);
                    int tileY = (int) (mouseEvent.getY() / GameConfig.TILE_SIZE);
                    int mouseX = tileX * GameConfig.TILE_SIZE;
                    int mouseY = tileY * GameConfig.TILE_SIZE;

                    if (TileMap.MAP_PATH[tileY][tileX] == 0) {
                        if (gameField.isPlacingNormalTower()) {
                            normal_preplace.relocate(mouseX, mouseY);
                        } else if (gameField.isPlacingMachineGunTower()) {
                            machine_gun_preplace.relocate(mouseX, mouseY);
                        }
                    }
                }
            }
        });

        root.getChildren().addAll(next_wave, buy_normal_tower, buy_machine_gun_tower, upgrade, sell);
        next_wave.setVisible(false);

        timer = new AnimationTimer()
        {
            public void handle(long currentTimeNs)
            {
                if (TowerDefense.startGame.isResetGame) {
                    gameStage = new GameStage();
                    gameField = new GameField();
                    TowerDefense.startGame.isResetGame = false;
                }
                gameField.getReinforcements().update();
                gameField.getReinforcements().render(gc);

                if (gameField.getReinforcements().isReachedEndPoint()){
                    gameField.getReinforcements().destroyReinforcements();
                }

                theScene.setOnKeyPressed(keyEvent -> {
                    if (keyEvent.getCode() == KeyCode.ESCAPE){
                        this.stop();
                        Text text = new Text("PAUSE");
                        text.setFill(Color.TOMATO);
                        text.setFont( Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 60));
                        text.setTextAlignment(TextAlignment.CENTER);
                        text.relocate(GameConfig.CANVAS_WIDTH/2.0 - 70, GameConfig.CANVAS_HEIGHT/2.0 - 70);

                        Rectangle back = new Rectangle(GameConfig.CANVAS_WIDTH, GameConfig.CANVAS_HEIGHT);
                        back.setOpacity(0.6);
                        root.getChildren().addAll(back, text);
                        theScene.setOnKeyPressed(keyEvent1 -> {
                            if (keyEvent1.getCode() == KeyCode.ESCAPE){
                                this.start();
                                root.getChildren().removeAll(text, back);
                            }
                        });
                    }
                });

                if (!gameField.getEnemies().isEmpty())
                {
                    for (Tower tower : gameField.getTowers())
                    {
                        for (int i = 0; i<gameField.getEnemies().size(); i++)
                        {
                            if (tower.checkEnemyInRange(gameField.getEnemies().get(i)))
                            {
                                if (tower.getTarget() == null)
                                {
                                    tower.setTarget(gameField.getEnemies().get(i));
                                }
                                else {
                                    tower.update();
                                }
                            }
                        }
                    }
                }
                if(!gameField.isWaveOver())
                {
                    gameField.spawnEnemies();
                }
                for (int i=0; i<gameField.getEnemies().size(); i++)
                {
                    gameField.getEnemies().get(i).update();
                    gameField.getEnemies().get(i).render(gc);
                    if (gameField.getEnemies().get(i).getPosX() >= GameConfig.GAME_WIDTH )
                    {
                        root.getChildren().remove(gameField.getEnemies().get(i).getImageV());
                        gameStage.takeDamage(gameField.getEnemies().get(i));
                        gameStage.update();
                        gameField.getEnemies().remove(i);

                    }
                }

                if (gameField.isWaveOver())
                {
                    next_wave.setVisible(true);
                    next_wave.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            gameField.setWaveCount();
                            gameField.calculateWavePower();
                            System.out.println(gameField.getTankerNumber());
                            gameField.setSpawning(true);
                            next_wave.setVisible(false);

                        }
                    });
                }

                if (gameStage.isGameOver()){

                    for (Enemy enemy: gameField.getEnemies()){
                        root.getChildren().remove(enemy.getImageV());
                    }
                    for (Tower tower: gameField.getTowers()){
                        tower.delete();
                    }
                    gameStage.reset();
                    stage.close();
                    timer.stop();
//                    resetGame(gameStage, gameField);
                    EndGame endGame = new EndGame();
                    try {
                        endGame.createEndGameContent().show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }


}
