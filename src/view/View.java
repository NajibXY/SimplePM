/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Game;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Corridor;
import model.Corridor.CandyType;
import static model.Corridor.CandyType.EMPTY;
import static model.Corridor.CandyType.SUPER;
import model.Entity;
import model.MonsterDoor;
import model.Tile;
import util.Direction;

/**
 *
 * @author Najib EL KHADIR
 */
public class View extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Observer observer;
    private Game game;

    private TilePane scorePane;
    private TilePane gridPane;
    private BorderPane rootPane;

    private Stage stage;

    private void initObservable() {
        this.observer = (Observable o, Object arg) -> {
            Platform.runLater(() -> {
                if (!this.game.isFinished()) {
                    this.draw();
                    this.display();
                }
            });
        };
        this.game.addObserver(this.observer);
        this.game.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.game = new Game();
        this.stage = primaryStage;
        this.stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            this.game.stop();
            this.stage.close();
        });
        this.initObservable();
        this.draw();
        this.display();
    }

    private void draw() {
        Tile[][] board = this.game.getBoard();
        this.gridPane = new TilePane();

        for (int y = 0; y < this.game.getDimension(); y++) {
            for (int x = 0; x < this.game.getDimension(); x++) {
                Tile tile = board[x][y];
                StackPane pane = new StackPane();
                Rectangle rect = new Rectangle(30, 30);
                pane.getChildren().add(rect);
                if (tile instanceof Corridor) {
                    if (tile instanceof MonsterDoor) {
                        rect.setFill(Color.GREY);
                    } else {
                        rect.setFill(Color.BLUE);
                    }

                    Corridor corr = ((Corridor) tile);
                    CandyType type = corr.getType();
                    if (type != EMPTY) {
                        Circle candy = new Circle(15, 15, 5, Color.WHITE);
                        if(type == SUPER){
                            candy.setRadius(10);
                        }
                        pane.getChildren().add(candy);
                        candy.requestFocus();
                    }

                    Entity entity = corr.getEntity();
                    if (entity != null) {
                            int startingAngle;
                            switch (entity.getDirection()) {
                                case LEFT:
                                    startingAngle = 180;
                                    break;
                                case DOWN:
                                    startingAngle = 90;
                                    break;
                                case RIGHT:
                                    startingAngle = 0;
                                    break;
                                case UP:
                                    startingAngle = 270;
                                    break;
                                default:
                                    startingAngle = 0;
                            }
                            
                            ImageView imageView = new ImageView(entity.getSprite());
                            imageView.setRotate(imageView.getRotate() + startingAngle);
                            SnapshotParameters params = new SnapshotParameters();
                            Image rotatedImage = imageView.snapshot(params, null);
                            params.setFill(Color.TRANSPARENT);
                            
                            ImagePattern imagePattern = new ImagePattern(rotatedImage);
                            rect.setFill(imagePattern);
                            /*
                            Arc pacman = new Arc(0, 0, 10, 10, startingAngle, 270);
                            pacman.setType(ArcType.ROUND);
                            pacman.setFill(Color.YELLOW);
                            pane.getChildren().add(pacman);
                            pacman.requestFocus();*/
                    }
                } else {
                    rect.setFill(Color.GREEN);
                }
                this.gridPane.getChildren().add(pane);
            }
        }

        
        this.drawScore();

        this.rootPane = new BorderPane();
        this.rootPane.setTop(this.scorePane);
        this.rootPane.setBottom(this.gridPane);
    }

    
    private void drawScore(){
        this.scorePane = new TilePane();
        int rectHeight = this.game.getDimension() * 30 / 3;
        StackPane scoreStack = new StackPane();
        Rectangle scoreRect = new Rectangle(rectHeight, 60);
        Text score = new Text(27, 27, String.valueOf(this.game.getScore()));
        score.setFill(Color.WHITE);
        scoreStack.getChildren().addAll(scoreRect, score);
        this.scorePane.setMaxSize(this.game.getDimension() * 30, 60);
        this.scorePane.getChildren().addAll(scoreStack);
    }
    
    private void display() {
        int sceneHeight = this.game.getDimension() * 30;
        int sceneWidth = this.game.getDimension() * 30 + 60;
        Scene scene = new Scene(this.rootPane, sceneHeight, sceneWidth);

        scene.setOnKeyReleased((event) -> {
            switch (event.getCode()) {
                default :
                    this.game.updatePacmanDirection(Direction.get(event.getCode()));
            }
            this.game.update();
        });

        Image icon = new Image(getClass().getResourceAsStream("/sprites/start_screen_logo.png"));
        this.stage.getIcons().add(icon);
        this.stage.setScene(scene);
        this.stage.sizeToScene();
        this.stage.centerOnScreen();
        this.stage.setResizable(false);
        this.stage.setTitle("Simple Pac-man");
        this.stage.show();
    }

}
