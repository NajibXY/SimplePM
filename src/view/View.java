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
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Tile;

/**
 *
 * @author Najib EL KHADIR
 */
public class View extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private Observer observer;
    private Game game;
    
    
    private TilePane tilePane;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.game = new Game();
        this.stage = primaryStage;
        this.stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            this.game.stop();
            this.stage.close();
        });
        this.draw();
        this.display();
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

    private void draw() {
        Tile[][] board = this.game.getBoard();
        this.tilePane = new TilePane();
    }

    private void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
