package towerdefense.component;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;

import java.io.IOException;

public class EndGame {
    private static Font font;
    private MenuBox menuBox;
    public EndGame(){


    }

    public Stage createEndGameContent() throws IOException{
        Stage createEndGame = new Stage();

        StackPane root = new StackPane();
        root.setPrefSize(GameConfig.CANVAS_WIDTH, GameConfig.CANVAS_HEIGHT);

        ImageView background = new ImageView(new Image("file:src/Assets/ui/gameover.png"));
        root.getChildren().add(background);

        font = Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 36);

        MenuItem try_again = new MenuItem("TRY AGAIN");
        try_again.setOnMouseClicked(mouseEvent -> {
            menuBox.hide();
            createEndGame.close();
            TowerDefense.startGame.isResetGame = true;
            TowerDefense.startGame.game.startGame();

        });

        MenuItem main_menu = new MenuItem("MAIN MENU");
        main_menu.setOnMouseClicked(mouseEvent -> {
            menuBox.hide();
            createEndGame.close();
            try {
                TowerDefense.startGame.isResetGame = true;
                TowerDefense.startGame.createContent().show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        menuBox = new MenuBox("GAME OVER",
                try_again,
                main_menu);

        root.getChildren().add(menuBox);
        root.setAlignment(Pos.CENTER);

        Scene endScene = new Scene(root);
        createEndGame.setScene(endScene);

        return createEndGame;



    }

    private static class MenuBox extends StackPane {
        public MenuBox(String tile, EndGame.MenuItem... items){
            Rectangle background = new Rectangle(400, 700);
            background.setOpacity(0.5);

            DropShadow shadow = new DropShadow(7, 5, 0, Color.BLACK);
            shadow.setSpread(0.8);

            Text text = new Text(tile);
            text.setFont(font);
            text.setFill(Color.WHITE);

            Line hSep = new Line();
            hSep.setEndX(250);
            hSep.setStroke(Color.DARKGREEN);
            hSep.setOpacity(0.4);

            Line vSep = new Line();
            vSep.setStartX(300);
            vSep.setEndX(300);
            vSep.setEndY(600);
            vSep.setStroke(Color.DARKGREEN);
            vSep.setOpacity(0.4);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(text);
            vBox.setPadding(new Insets(100, 0, 0, 0));
            vBox.setAlignment(Pos.TOP_CENTER);

            VBox vbox = new VBox();
            vbox.setAlignment(Pos.TOP_CENTER);
            vbox.setPadding(new Insets(350, 0, 0, 0));
            vbox.getChildren().addAll(items);

            System.out.print(vbox.getPrefHeight());
            vbox.setAlignment(Pos.TOP_CENTER);

//            VBox endBox = new VBox();
//            Text end = new Text("Luu Thi Hoai Thu Corp");
//
//            end.setFill(Color.WHITE);
//            end.setFont(Font.font("", FontWeight.LIGHT, 13));
//            endBox.getChildren().addAll(end);
//            endBox.setAlignment(Pos.BOTTOM_CENTER);
//            endBox.setPrefSize(200, 50);

            getChildren().addAll(background, vBox, vbox);
        }

        public void show(){
            setVisible(true);

            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.4), this);

            translateTransition.setToY(0);
            translateTransition.play();
        }

        public void hide(){
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.4), this);

            translateTransition.setToY(-350);
            translateTransition.setOnFinished(actionEvent -> setVisible(false));
            translateTransition.play();
        }
    }

    private static class MenuItem extends StackPane{
        public MenuItem(String name){
            Rectangle background = new Rectangle(300, 48);

            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop(0, Color.BLACK),
                    new Stop(0.2, Color.DARKGREY));

            background.setFill(gradient);
            background.setVisible(false);
            background.setEffect(new DropShadow(5, 0, 0, Color.BLACK));

            Text text = new Text(name);
            text.setFill(Color.LIGHTGREY);
            text.setFont(font);
            getChildren().addAll(background, text);
            setAlignment(Pos.CENTER);
            setOnMouseEntered(mouseEvent -> {
                background.setVisible(true);
                text.setFill(Color.WHITE);
            });

            setOnMouseExited(mouseEvent -> {
                background.setVisible(false);
                text.setFill(Color.LIGHTGREY);
            });

            setOnMousePressed(mouseEvent -> {
                background.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });

            setOnMouseReleased(mouseEvent -> {
                background.setFill(gradient);
                text.setFill(Color.WHITE);
            });
        }
    }

}
