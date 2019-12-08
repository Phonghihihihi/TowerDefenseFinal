package towerdefense.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import towerdefense.component.*;


public class TowerDefense extends Application {
//    public static Group root = new Group();
    public Enum status = GameConfig.STATUS.START;
    public static StartGame startGame = new StartGame();
    public static Game game;
    public static EndGame endGame;

    @Override
    public void start(Stage stage) throws Exception {
        // Set up stage and main BorderPane
        stage = startGame.createContent();
        stage.show();

//        Canvas canvas = new Canvas(GameConfig.CANVAS_WIDTH, GameConfig.CANVAS_HEIGHT);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        root.getChildren().add(canvas);
//        Scene theScene = new Scene(root);
//
//        stage.setTitle(GameConfig.GAME_NAME);
//        stage.setScene(theScene);
//        TileMap.drawUI(gc);
//        TileMap.drawMap(gc);
//        GameStage gameStage = new GameStage();
//        GameField gameField = new GameField();
//
//        Button next_wave = new Button("Next Wave");
//        next_wave.relocate(1200,400);
//
//        Button buy_normal_tower = new Button ("", new ImageView(new Image(GameConfig.NORMAL_TOWER_IMAGE_URL)));
//        buy_normal_tower.relocate(1200,250);
//        buy_normal_tower.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                gameField.setPlacingNormalTower(true);
//            }
//        });
//
//        Button buy_machine_gun_tower = new Button ("", new ImageView(new Image(GameConfig.MACHINE_GUN_TOWER_IMAGE_URL)));
//        buy_machine_gun_tower.relocate(1200,150);
//        buy_machine_gun_tower.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                gameField.setPlacingMachinGunTower(true);
//            }
//        });
//
//        Button upgrade = new Button ("Upgrade");
//        upgrade.relocate(1200,350);
//        upgrade.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                if (gameField.isUpgradingTower())
//                {
//                    gameField.setUpgradingTower(true);
//                }
//                gameField.setSellingTower(false);
//            }
//        });
//
//        Button sell = new Button ("Sell");
//        sell.relocate(1200,450);
//        sell.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                gameField.setSellingTower(true);
//            }
//        });
//
//
//        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//                int tileX = (int)(mouseEvent.getX() / GameConfig.TILE_SIZE) ;
//                int tileY = (int)(mouseEvent.getY() / GameConfig.TILE_SIZE) ;
//                int mouseX = tileX * GameConfig.TILE_SIZE;
//                int mouseY = tileY * GameConfig.TILE_SIZE;
//                if (TileMap.MAP_PATH[tileY][tileX] == 0) {
//                    if (gameField.isPlacingNormalTower()) {
//                        NormalTower t1 = new NormalTower(mouseX, mouseY, 50, 50);
//                        t1.render(gc);
//                        gameField.getTowers().add(t1);
//                        gameField.setPlacingNormalTower(false);
//                        TileMap.MAP_PATH[tileY][tileX] = 1;
//                    }
//                    else if (gameField.isPlacingMachinGunTower()) {
//                        MachineGunTower t2 = new MachineGunTower(mouseX,mouseY,50, 50);
//                        t2.render(gc);
//                        gameField.getTowers().add(t2);
//                        gameField.setPlacingMachinGunTower(false);
//                        TileMap.MAP_PATH[tileY][tileX] = 1;
//                    }
//                }
//                else if (TileMap.MAP_PATH[tileY][tileX] == 1)
//                {
//                    for (int i = 0; i < gameField.getTowers().size(); i++) {
//                        if (gameField.getTowers().get(i).getPosX() == mouseX && gameField.getTowers().get(i).getPosY() == mouseY) {
//                            if (gameField.isSellingTower()) {
//                                gameField.getTowers().get(i).delete();
//                                gameField.getTowers().remove(i);
//                                TileMap.MAP_PATH[tileY][tileX] = 0;
//                                gameField.setSellingTower(false);
//                            }
//                             else if (gameField.isUpgradingTower())
//                            {
//                                gameField.getTowers().get(i).upgrade();
//                            }
//                        }
//                    }
//                }
//            }
//        });
//
//        root.getChildren().addAll(next_wave, buy_normal_tower, buy_machine_gun_tower, upgrade, sell);
//        next_wave.setVisible(false);
//
//
//        AnimationTimer timer = new AnimationTimer()
//        {
//            public void handle(long currentTimeNs)
//            {
//                gameField.getReinforcements().update();
//                gameField.getReinforcements().render(gc);
////                System.out.println(gameField.getReinforcements().getPosX());
//
//                if (!gameField.getEnemies().isEmpty())
//                {
//                    for (Tower tower : gameField.getTowers())
//                    {
//                        for (int i = 0; i<gameField.getEnemies().size(); i++)
//                        {
//                            if (tower.checkEnemyInRange(gameField.getEnemies().get(i)))
//                            {
//                                if (tower.getTarget() == null)
//                                {
//                                    tower.setTarget(gameField.getEnemies().get(i));
//                                }
//                                else {
//                                    tower.update();
//                                }
//                            }
//                        }
//                    }
//                }
//                if(!gameStage.isWaveOver())
//                {
//                    gameField.spawnEnemies();
//                }
//                for (int i=0; i<gameField.getEnemies().size(); i++)
//                {
//                    gameField.getEnemies().get(i).update();
//                    gameField.getEnemies().get(i).render(gc);
//                    if (gameField.getEnemies().get(i).getPosX() >= GameConfig.GAME_WIDTH)
//                    {
//                        root.getChildren().remove(gameField.getEnemies().get(i).getImageV());
//                        gameField.getEnemies().remove(i);
//                    }
//                }
//
//                if (!gameField.isSpawning() && gameField.getEnemies().isEmpty())
//                {
//                    gameStage.setWaveOver(true);
//                    next_wave.setVisible(true);
//                    next_wave.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                        @Override
//                        public void handle(MouseEvent mouseEvent) {
//                            gameStage.setWaveOver(false);
//                            gameField.setSpawning(true);
//                            next_wave.setVisible(false);
//
//                        }
//                    });
//                }
//            }
//        };
//
//        if (startGame.isPlaying){
//            timer.start();
//            stage.show();
//        }
//
//

    }

    public static void main(String[] args) {
        System.out.println(GameConfig.CANVAS_WIDTH + "x" + GameConfig.CANVAS_HEIGHT);
        launch(args);
    }
}
