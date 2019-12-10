package towerdefense.component;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.util.Duration;
import towerdefense.ui.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class StartGame {
    private static Font font;
    private static final Font FONT = Font.font("", FontWeight.BOLD, 18);
    private MenuBox menuBox;
    private String path = "src/Assets/Music/Menu.mp3";
    private MediaPlayer menuMusic = new MediaPlayer(new Media(new File(path).toURI().toString()));
    private boolean isFirstTimePlay = true;
    public boolean isResetGame = false;
    public Game game;


    public StartGame() {

    }

    private void createNewGame() throws FileNotFoundException, InterruptedException{

    }

    private Scene createHowToPlay() throws IOException{
        StackPane root = new StackPane();
        ImageView imageView = new ImageView(new Image("file:src/Assets/ui/howtoplay.png"));
        imageView.setFitWidth(GameConfig.CANVAS_WIDTH);
        imageView.setFitHeight(GameConfig.CANVAS_HEIGHT);

        VBox guideBox = new VBox();
        Text guide = new Text("Press Space to get back to Main Menu");
        guide.setTextAlignment(TextAlignment.CENTER);
        guide.setFill(Color.WHITE);
        guide.setFont(FONT);
        guide.setOpacity(1);
        guideBox.getChildren().add(guide);
        guideBox.setAlignment(Pos.BOTTOM_CENTER);
        guideBox.setPrefSize(200, 50);

        root.getChildren().addAll(imageView, guideBox);
        return new Scene(root);
    }

    private Scene createCredit(){
        StackPane root = new StackPane();
        root.setPrefSize(GameConfig.CANVAS_WIDTH, GameConfig.CANVAS_HEIGHT);
        Rectangle background = new Rectangle(GameConfig.CANVAS_WIDTH, GameConfig.CANVAS_HEIGHT);
        background.setOpacity(0.6);

        ImageView img = new ImageView(new Image("file:src/Assets/ui/background.jpg"));
        img.setFitWidth(GameConfig.CANVAS_WIDTH);
        img.setFitHeight(GameConfig.CANVAS_HEIGHT);
        root.getChildren().add(img);
        font = Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 30);

        Text credit = new Text("TOWER DEFENSE\n" +
                "by\n" +
                "Luu Thi Hoai Thu Corporation");
        credit.setTextAlignment(TextAlignment.CENTER);
        credit.setFill(Color.WHITE);
        credit.setLineSpacing(2);
        credit.setFont(FONT);
        credit.setOpacity(1);

        VBox guideBox = new VBox();
        Text guide = new Text("Press Space to get back to Main Menu");
        guide.setTextAlignment(TextAlignment.CENTER);
        guide.setFill(Color.WHITE);
        guide.setFont(FONT);
        guide.setOpacity(1);
        guideBox.getChildren().add(guide);
        guideBox.setAlignment(Pos.BOTTOM_CENTER);
        guideBox.setPrefSize(200, 50);


        root.getChildren().addAll(background, credit, guideBox);
        StackPane.setAlignment(credit, Pos.CENTER);
        return new Scene(root);
    }

    public Stage createContent() throws IOException {
        menuMusic.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                menuMusic.seek(Duration.ZERO);
            }
        });
        //menuMusic.setVolume(0.1);
        menuMusic.play();
        Stage createContent = new Stage();
        Scene creditScene = createCredit();
        Scene howtoplayScene = createHowToPlay();

        StackPane root = new StackPane();
        root.setPrefSize(GameConfig.CANVAS_WIDTH, GameConfig.CANVAS_HEIGHT);

        ImageView img = new ImageView(new Image("file:src/Assets/ui/background.jpg"));
        img.setFitWidth(GameConfig.CANVAS_WIDTH);
        img.setFitHeight(GameConfig.CANVAS_HEIGHT);
        root.getChildren().add(img);

        font = Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 30);

        MenuItem quit = new MenuItem("QUIT");
        quit.setOnMouseClicked(mouseEvent -> System.exit(0));

        MenuItem credit = new MenuItem("CREDIT");
        credit.setOnMouseClicked(mouseEvent -> {
            createContent.setScene(creditScene);
        });

        MenuItem howtoplay = new MenuItem("HOWTOPLAY");
        howtoplay.setOnMouseClicked(mouseEvent -> {
            createContent.setScene(howtoplayScene);
        });

        MenuItem start = new MenuItem("PLAY");
        menuBox = new MenuBox("TOWER DEFENSE",
                start,
                howtoplay,
                credit,
                quit);

        root.getChildren().add(menuBox);
        root.setAlignment(Pos.CENTER);

        Scene startScene = new Scene(root);

        creditScene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE){
                menuBox.show();
                createContent.setScene(startScene);
            }
        });

        howtoplayScene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE){
                createContent.setScene(startScene);
            }
        });

        start.setOnMouseClicked(mouseEvent -> {
            menuBox.hide();
            menuMusic.stop();
            createContent.close();
            if (isFirstTimePlay){
                game = new Game();
                isFirstTimePlay = false;
            }
            game.startGame();

        });


        createContent.setScene(startScene);

        return createContent;
    }




    private static class MenuBox extends StackPane{
        public MenuBox(String tile, StartGame.MenuItem... items){
            Rectangle background = new Rectangle(400, 720);
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
            vbox.setPadding(new Insets(300, 0, 0, 0));
            vbox.getChildren().addAll(items);

            System.out.print(vbox.getPrefHeight());
            vbox.setAlignment(Pos.TOP_CENTER);

            VBox endBox = new VBox();
            Text end = new Text("Luu Thi Hoai Thu Corp");

            end.setFill(Color.WHITE);
            end.setFont(Font.font("", FontWeight.LIGHT, 13));
            endBox.getChildren().addAll(end);
            endBox.setAlignment(Pos.BOTTOM_CENTER);
            endBox.setPrefSize(200, 50);

            getChildren().addAll(background, vBox, endBox, vbox);
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
            Rectangle background = new Rectangle(300, 24);

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
