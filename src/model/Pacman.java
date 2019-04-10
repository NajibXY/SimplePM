/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import util.Direction;
import controller.Game;
import javafx.geometry.Point2D;

/**
 *
 * @author Najib EL KHADIR
 */
public class Pacman extends Entity{
    
    
    
    public Pacman(Direction currentDir, Point2D coords, String spriteId, Game game, int interval) {
        super(currentDir, coords, spriteId, game, interval);
    }
   
    
    
    @Override
    protected Direction getNextDirection() {
        return this.currentDir;
    }
    
    @Override
    public boolean canKill(Entity enemy) {
        return false;
    }
    
     /*
    private boolean power;
    
    public boolean isPower(){
        return this.power;
    }
    
    public void startPower() {
        this.game.notifyPowerToGhosts();
        if(!this.isPower()) {
            this.setSprite(new Image(getClass().getResourceAsStream("sprites/power.png")));
            this.power = true;
            this.setInterval(this.interval / 2);
        }
        if (this.powerTimeline.getStatus() == RUNNING) {
            this.powerTimeline.jumpTo(Duration.ZERO);
        } else {
            this.powerTimeline.play();
        }
    }*/

    /*
    public void endPower() {
        this.setSprite(this.getDefaultSprite());
        this.power = false;
        this.game.notifyEndToGhosts();
    }

    @Override
    public boolean canKill(Entity enemy) {
        return (enemy instanceof Monster && this.power && ((Monster)enemy).isScared());
    }
    */
}
