/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import util.Direction;
import controller.Game;
import static javafx.animation.Animation.Status.RUNNING;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;

/**
 *
 * @author Najib EL KHADIR
 */
public class Pacman extends Entity{
    
    private boolean power;
    private final Timeline powerTimeline;
    
    public Pacman(Direction currentDir, Point2D coords, String spriteId, Game game, int interval) {
        super(currentDir, coords, spriteId, game, interval);
        this.power = false;
        this.powerTimeline = new Timeline(new KeyFrame(Duration.seconds(10), (e) -> {
            this.endPower();
        }));
    }
   
    public boolean isPower(){
        return this.power;
    }
    
     public void startPower() {
        if(!this.isPower()) {
            //this.setColor(Color.ORANGE);
            this.power = true;
            this.setInterval(this.interval / 2);
        }
        if (this.powerTimeline.getStatus() == RUNNING) {
            this.powerTimeline.jumpTo(Duration.ZERO);
        } else {
            this.powerTimeline.play();
        }
    }

    public void endPower() {
        System.out.println("endpower");
        this.setInterval(this.interval * 2);
        this.power = false;
    }

    @Override
    public boolean canKill(Entity enemy) {
        return (enemy instanceof Monster && this.power  );
    }
    
    @Override
    protected Direction getNextDirection() {
        return this.currentDir;
    }
    
    
}
