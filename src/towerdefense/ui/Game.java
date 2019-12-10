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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import towerdefense.component.*;
import towerdefense.component.bullet.Bullet;
import towerdefense.component.enemy.Enemy;
import towerdefense.component.tower.MachineGunTower;
import towerdefense.component.tower.NormalTower;
import towerdefense.component.tower.SniperTower;
import towerdefense.component.tower.Tower;

import java.io.File;
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
    private MediaPlayer theme = new MediaPlayer(new Media(new File("src/Assets/Music/Theme.mp3").toURI().toString()));
    boolean isResetGame = false;

    public void startGame(){
        timer.start();
        stage.show();
        theme.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                theme.seek(Duration.ZERO);
            }
        });
        theme.setVolume(0.1);
        theme.play();
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

        /*theme.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                theme.seek(Duration.ZERO);
            }
        });
        theme.play();*/

        Button next_wave = new Button("Next Wave");
        next_wave.setOnMouseClicked(mouseEvent -> {
            gameField.setWaveCount();
            gameStage.updateWave(gameField.getWaveCount());
            gameStage.setEndWaveReward(true);
            gameField.calculateWavePower();
            gameField.setSpawning(true);
            next_wave.setVisible(false);

        });
        next_wave.relocate(1200,400);

        Button call_reinforcements = new Button("Send Help!");
        call_reinforcements.relocate(1200, 600);

        ImageView normal_preplace = new ImageView(new Image("file:src/Assets/Tower/Normal_preplace.png"));
        ImageView machine_gun_preplace = new ImageView(new Image("file:src/Assets/Tower/250_preplace.png"));
        ImageView sniper_preplace = new ImageView(new Image("file:src/Assets/Tower/Sniper_preplace.png"));
        Circle circle_preplace_1 = new Circle(GameConfig.NORMAL_TOWER_RANGE,Color.TRANSPARENT);
        circle_preplace_1.setStroke(Color.BLACK);

        Circle circle_preplace_2 = new Circle(GameConfig.MACHINE_GUN_TOWER_RANGE,Color.TRANSPARENT);
        circle_preplace_2.setStroke(Color.BLACK);

        Circle circle_preplace_3 = new Circle(GameConfig.SNIPER_TOWER_RANGE, Color.TRANSPARENT);
        circle_preplace_3.setStroke(Color.BLACK);

        Text normal_price = new Text( "$" + GameConfig.NORMAL_TOWER_PRICE);
        Text machine_gun_price = new Text("$" + GameConfig.MACHINE_GUN_TOWER_PRICE);
        Text sniper_price = new Text("$" + GameConfig.SNIPER_TOWER_PRICE);

        normal_price.setFill(Color.LEMONCHIFFON);
        machine_gun_price.setFill(Color.LEMONCHIFFON);
        sniper_price.setFill(Color.LEMONCHIFFON);

        normal_price.setFont( Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 32));
        machine_gun_price.setFont( Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 32));
        sniper_price.setFont( Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 32));

        normal_price.relocate(1260, 150);
        machine_gun_price.relocate(1260, 200);
        sniper_price.relocate(1260, 250);

        root.getChildren().addAll(normal_price, machine_gun_price, sniper_price);


        Button buy_normal_tower = new Button ("", new ImageView(new Image("file:src/Assets/Tower/Normal Button.png")));
        buy_normal_tower.relocate(1200,150);
        buy_normal_tower.setBackground(null);
        buy_normal_tower.setOnAction(actionEvent -> {
            gameField.setPlacingNormalTower(true);
            gameField.setPlacingMachineGunTower(false);
            gameField.setPlacingSniperTower(false);
            if (!root.getChildren().contains(normal_preplace))  {
                root.getChildren().addAll(normal_preplace, circle_preplace_1);
            }
            if (root.getChildren().contains(machine_gun_preplace)) {
                root.getChildren().removeAll(machine_gun_preplace, circle_preplace_2);
            }
            else if (root.getChildren().contains(sniper_preplace))
            {
                root.getChildren().removeAll(sniper_preplace, circle_preplace_3);
            }
        });

        Button buy_machine_gun_tower = new Button ("", new ImageView(new Image("file:src/Assets/Tower/Machine Gun Button.png")));
        buy_machine_gun_tower.relocate(1200,200);
        buy_machine_gun_tower.setBackground(null);
        buy_machine_gun_tower.setOnAction(actionEvent -> {
            gameField.setPlacingMachineGunTower(true);
            gameField.setPlacingNormalTower(false);
            gameField.setPlacingSniperTower(false);
            if(!root.getChildren().contains(machine_gun_preplace)) {
                root.getChildren().addAll(machine_gun_preplace, circle_preplace_2);
            }
            if (root.getChildren().contains(normal_preplace)) {
                root.getChildren().removeAll(normal_preplace, circle_preplace_1);
            }
            else if (root.getChildren().contains(sniper_preplace))
            {
                root.getChildren().removeAll(sniper_preplace, circle_preplace_3);
            }
        });

        Button buy_sniper_tower = new Button ("", new ImageView(new Image("file:src/Assets/Tower/Sniper Button.png")));
        buy_sniper_tower.relocate(1200,250);
        buy_sniper_tower.setBackground(null);
        buy_sniper_tower.setOnAction(actionEvent -> {
            gameField.setPlacingSniperTower(true);
            gameField.setPlacingMachineGunTower(false);
            gameField.setPlacingNormalTower(false);
            if (!root.getChildren().contains(sniper_preplace))
            {
                root.getChildren().addAll(sniper_preplace, circle_preplace_3);
            }
            if (root.getChildren().contains(machine_gun_preplace)) {
                root.getChildren().removeAll(machine_gun_preplace, circle_preplace_2);
            }
            else if (root.getChildren().contains(normal_preplace))
            {
                root.getChildren().removeAll(normal_preplace, circle_preplace_1);
            }
        });

        Button upgrade = new Button ("Upgrade");
        upgrade.relocate(1200,350);
        upgrade.setDisable(true);
        upgrade.setVisible(false);
        upgrade.setOnAction(actionEvent->{
            if (gameStage.getMoney() >= gameField.getUpgradingTower().getPrice()*gameField.getUpgradingTower().getLevel()){
                gameStage.setMoney(gameStage.getMoney() - gameField.getUpgradingTower().getPrice()*gameField.getUpgradingTower().getLevel());
                gameField.getUpgradingTower().upgrade();
            }
        });

        Button sell = new Button ("Sell");
        sell.relocate(1200,450);
        sell.setVisible(false);
        sell.setDisable(true);
        sell.setOnAction(actionEvent -> {
            gameStage.setMoney(gameStage.getMoney() + gameField.getSellingTower().getPrice() / 2);
            TileMap.MAP_PATH[gameField.getSellingTower().getTileY()][gameField.getSellingTower().getTileX()] = 0;
            gameField.getSellingTower().delete();
            gameField.getTowers().remove(gameField.getSellingTower());
            gameField.setSellingTower(null);
        });


        theScene.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getX() <= GameConfig.GAME_WIDTH) {

                int tileX = (int) (mouseEvent.getX() / GameConfig.TILE_SIZE);
                int tileY = (int) (mouseEvent.getY() / GameConfig.TILE_SIZE);
                int mouseX = tileX * GameConfig.TILE_SIZE;
                int mouseY = tileY * GameConfig.TILE_SIZE;

                if (TileMap.MAP_PATH[tileY][tileX] == 0) {
                    if (gameField.isPlacingNormalTower() && gameStage.getMoney() >= GameConfig.NORMAL_TOWER_PRICE) {
                        NormalTower t1 = new NormalTower(mouseX, mouseY, 50, 50);
                        t1.buildTower();
                        t1.render(gc);
                        gameField.getTowers().add(t1);


                        gameField.setPlacingNormalTower(false);
                        TileMap.MAP_PATH[tileY][tileX] = 1;
                        root.getChildren().removeAll(normal_preplace, circle_preplace_1);
                        gameStage.setMoney(gameStage.getMoney() - GameConfig.NORMAL_TOWER_PRICE);
                    } else if (gameField.isPlacingMachineGunTower() && gameStage.getMoney()>= GameConfig.MACHINE_GUN_TOWER_PRICE) {
                        MachineGunTower t2 = new MachineGunTower(mouseX, mouseY, 50, 50);
                        t2.buildTower();
                        t2.render(gc);
                        gameField.getTowers().add(t2);

                        gameField.setPlacingMachineGunTower(false);
                        TileMap.MAP_PATH[tileY][tileX] = 1;
                        root.getChildren().removeAll(machine_gun_preplace, circle_preplace_2);
                        gameStage.setMoney(gameStage.getMoney() - GameConfig.MACHINE_GUN_TOWER_PRICE);
                    }
                    else if (gameField.isPlacingSniperTower() && gameStage.getMoney()>= GameConfig.SNIPER_TOWER_PRICE) {
                        SniperTower t3 = new SniperTower(mouseX, mouseY, 50, 50);
                        t3.buildTower();
                        t3.render(gc);
                        gameField.getTowers().add(t3);
                        gameField.setPlacingSniperTower(false);
                        TileMap.MAP_PATH[tileY][tileX] = 1;
                        root.getChildren().removeAll(sniper_preplace, circle_preplace_3);
                        gameStage.setMoney(gameStage.getMoney() - GameConfig.SNIPER_TOWER_PRICE);
                    }
                    gameField.setSellingTower(null);
                    upgrade.setDisable(true);
                    upgrade.setVisible(false);
                    sell.setDisable(true);
                    sell.setVisible(false);
                    root.getChildren().removeAll(normal_preplace, machine_gun_preplace);
                } else if (TileMap.MAP_PATH[tileY][tileX] == 1) {
                    for (Tower tower : gameField.getTowers()){
                        if (tower.getPosX() == mouseX && tower.getPosY() == mouseY)
                        {
                            gameField.setSellingTower(tower);
                            gameField.setUpgradingTower(tower);
                            upgrade.setDisable(false);
                            upgrade.setVisible(true);
//                            tower.updateLevel();
//                            tower.setVisible(true);
                            sell.setDisable(false);
                            sell.setVisible(true);
                            root.getChildren().removeAll(normal_preplace, machine_gun_preplace);
                        }
                    }


                }
            }
        });

        theScene.setOnMouseMoved(mouseEvent -> {
            if (mouseEvent.getX() < GameConfig.GAME_WIDTH) {
                int tileX = (int) (mouseEvent.getX() / GameConfig.TILE_SIZE);
                int tileY = (int) (mouseEvent.getY() / GameConfig.TILE_SIZE);
                int mouseX = tileX * GameConfig.TILE_SIZE;
                int mouseY = tileY * GameConfig.TILE_SIZE;

                if (TileMap.MAP_PATH[tileY][tileX] == 0) {
                    if (gameField.isPlacingNormalTower()) {
                        normal_preplace.relocate(mouseX, mouseY);
                        circle_preplace_1.setCenterX(mouseX + 39);
                        circle_preplace_1.setCenterY(mouseY + 39);

                    } else if (gameField.isPlacingMachineGunTower()) {
                        machine_gun_preplace.relocate(mouseX, mouseY);
                        circle_preplace_2.setCenterX(mouseX + 39);
                        circle_preplace_2.setCenterY(mouseY + 39);

                    }
                    else if (gameField.isPlacingSniperTower())
                    {
                        sniper_preplace.relocate(mouseX, mouseY);
                        circle_preplace_3.setCenterX(mouseX + 32);
                        circle_preplace_3.setCenterY(mouseY + 32);
                    }
                }
            }
        });

        root.getChildren().addAll(next_wave, call_reinforcements, buy_normal_tower, buy_machine_gun_tower, buy_sniper_tower, upgrade, sell);

        next_wave.setVisible(false);
        call_reinforcements.setVisible(false);

        timer = new AnimationTimer()
        {
            public void handle(long currentTimeNs) {
                if (TowerDefense.startGame.isResetGame) {
                    gameStage = new GameStage();
                    gameField = new GameField();
                    TowerDefense.startGame.isResetGame = false;
                }

                theScene.setOnKeyPressed(keyEvent -> {
                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        this.stop();
                        Text text = new Text("PAUSE");
                        text.setFill(Color.TOMATO);
                        text.setFont(Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 60));
                        text.setTextAlignment(TextAlignment.CENTER);
                        text.relocate(GameConfig.CANVAS_WIDTH / 2.0 - 70, GameConfig.CANVAS_HEIGHT / 2.0 - 70);

                        Rectangle back = new Rectangle(GameConfig.CANVAS_WIDTH, GameConfig.CANVAS_HEIGHT);
                        back.setOpacity(0.6);
                        root.getChildren().addAll(back, text);
                        theScene.setOnKeyPressed(keyEvent1 -> {
                            if (keyEvent1.getCode() == KeyCode.ESCAPE) {
                                this.start();
                                root.getChildren().removeAll(text, back);
                            }
                        });
                    }
                });

//check va cham

                for (Tower tower : gameField.getTowers())
                {
                    tower.drawCircle();
                    if (!gameField.getEnemies().isEmpty())
                    {
                        for (int i = 0; i<gameField.getEnemies().size(); i++)
                        {
                            if (tower.checkEnemyInRange(gameField.getEnemies().get(i)))
                            {
                                if (tower.getTarget() == null)
                                {
                                    tower.setTarget(gameField.getEnemies().get(i));
                                }
                            }
                            if (gameField.getEnemies().get(i).getHealth() <= 0)
                            {
                                gameField.getEnemies().get(i).death();
                                gameStage.getReward(gameField.getEnemies().get(i));
                                gameField.getEnemies().remove(i);
                            }
                        }
                    }
                    if (tower.getTarget()!= null) {
                        tower.update();
                    }
                    else {
                        tower.getBullet().Visible(false);
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
                        gameField.getEnemies().get(i).attack();
                        gameStage.takeDamage(gameField.getEnemies().get(i));
                        gameField.getEnemies().remove(i);

                    }

                    if (!gameField.getEnemies().isEmpty()){
                        if (gameField.getReinforcements().isBoomFallIntoEnemy(gameField.getEnemies().get(i))){
                            gameField.getEnemies().get(i).destroyEnemy();
                            gameField.getEnemies().get(i).remove_Health();
                            gameField.getEnemies().remove(i);
                        }
                    }


                }

                if (gameField.isWaveOver())
                {
                    next_wave.setVisible(true);
                    gameStage.getEndWaveReward(gameField.getWaveCount());
                }

                if (gameField.getEnemies().size() >= 5 && !gameField.isCallReinforcement()){
                    call_reinforcements.setVisible(true);
                    call_reinforcements.setOnMouseClicked(mouseEvent -> {
                        gameField.setCallReinforcements(true);
                        gameField.createNewPlane();
                        call_reinforcements.setVisible(false);
                    });
                }

                if (gameField.isCallReinforcement()){
                    gameField.getReinforcements().setVisible();
                    gameField.getReinforcements().update();
                    gameField.getReinforcements().render(gc);
                }

                if (gameField.getReinforcements().isReachedEndPoint()){
                    gameField.getReinforcements().destroyReinforcements();
                    gameField.setCallReinforcements(false);
                }


                if (gameStage.isGameOver()){

                    for (Enemy enemy: gameField.getEnemies()){
                        root.getChildren().remove(enemy.getImageV());
                        enemy.remove_Health();
                    }
                    for (Tower tower: gameField.getTowers()){
                        tower.delete();
                    }
                    gameField.getReinforcements().destroyReinforcements();
                    gameStage.reset();
                    gameStage.resetWave();
                    stage.close();
                    timer.stop();
                    theme.stop();
//                    resetGame(gameStage, gameField);
                    EndGame endGame = new EndGame();
                    try {
                        endGame.createEndGameContent().show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    gameStage.update();
                }
            }
        };
    }


}
