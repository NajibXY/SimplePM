/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Game;
import java.util.concurrent.atomic.AtomicBoolean;
import util.Direction;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 *
 * @author Najib EL KHADIR
 */
public abstract class Entity implements Runnable{
    protected Direction currentDir;
    protected Point2D coords;
    protected final Point2D initCoords;
    
    protected final Image sprite;
    
    protected final Thread thread;
    protected int interval;
    protected AtomicBoolean runnable;
    
    protected final Game game;
    protected boolean turnback;

    public Entity(Direction currentDir, Point2D coords, String spriteId, Game game, int interval) {
        this.currentDir = currentDir;
        this.coords = coords;
        this.initCoords = coords;
        
        this.sprite = new Image(getClass().getResourceAsStream("sprites/"+spriteId));
        
        this.interval = interval;
        this.thread = new Thread(this);
        this.runnable = new AtomicBoolean(false);
        
        this.game = game;
        
        this.turnback = false;
    }
    
    public void start() {
        this.runnable.set(true);
        this.thread.start();
    }
    
    public Direction getDirection() {
        return currentDir;
    }

    public void setDirection(Direction currentDir) {
        this.currentDir = currentDir;
    }

    public Point2D getCoords() {
        return coords;
    }

    public void move(Point2D coords) {
        this.coords = coords;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public boolean isTurnback() {
        return turnback;
    }

    public void setTurnback(boolean turnback) {
        this.turnback = turnback;
    }
    
    public void stop() {
        this.runnable.set(false);
    }
    
    protected abstract Direction getNextDirection();
    
   @Override
    public void run() {
        while (this.runnable.get()) {
            try {
                this.thread.sleep(this.interval);
                if (this instanceof Monster) {
                    //this.game.move(this, this.currentDir.getOpposed());
                    this.setTurnback(false);
                } else {
                    //this.game.move(this, this.getNextDirection());
                }
            } catch (InterruptedException ex) {
                System.out.println("Interrupted thread");
            }
        }
        
    }

    public abstract boolean canKill(Entity enemy);
    
    
    
}
